package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.CodeOrganBean;
import ncbank.beans.UserBean;
import ncbank.service.AccountService;
import ncbank.service.AutoService;
import ncbank.service.CodeOrganService;
import ncbank.validator.ExceptionMessage;

@Controller
@RequestMapping("/auto")
public class AutoController {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CodeOrganService codeOrganService;

	@Autowired
	private AutoService autoService;

	private static final Logger logger = LoggerFactory.getLogger(AutoController.class);

	// 조회 페이지
	@GetMapping("/transferAutoCheck")
	public String TransferAutoCheck(Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		int userNum = loginUserBean.getUser_num();
		List<AutoBean> autoList = autoService.getAuto(userNum);
		List<AccountBean> accounts = accountService.getAccount(userNum);
		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();

		// 각 AutoBean 객체에 code_organ_name 설정
		for (AutoBean auto : autoList) {
			for (CodeOrganBean codeOrgan : codeOrganNames) {
				if (auto.getCode_organ().equals(codeOrgan.getCode_organ())) {
					auto.setCode_organ_name(codeOrgan.getCode_organ_name());
					break;
				}
			}
		}

		model.addAttribute("autoList", autoList);
		model.addAttribute("accounts", accounts);
		model.addAttribute("codeOrganNames", codeOrganNames);

		return "auto/transferAutoCheck";
	}

	// 등록 페이지
	@GetMapping("/transferAuto")
	public String TransferAuto(Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		int userNum = loginUserBean.getUser_num();
		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);

		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
		model.addAttribute("codeOrganNames", codeOrganNames);

		// 폼 바인딩을 위해 빈 AutoBean을 모델에 추가
		model.addAttribute("autoBean", new AutoBean());

		return "auto/transferAuto";
	}

	// 등록 API
	@PostMapping("/transferAuto") // 자동이체 등록 폼 제출을 처리하는 메소드 추가
	public String addTransferAuto(@Valid @ModelAttribute("autoBean") AutoBean autoBean,
			@RequestParam("ac_password") String acPassword, Model model, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			logger.error("Validation errors: {}", result.getAllErrors());
			int userNum = loginUserBean.getUser_num();
			List<AccountBean> accounts = accountService.getAccount(userNum);
			model.addAttribute("accounts", accounts);

			List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
			model.addAttribute("codeOrganNames", codeOrganNames);
			return "auto/transferAuto";
		}
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		// 계좌 비밀번호 확인
		String accountNumber = autoBean.getFrom_account();
		AccountBean accounts = accountService.getAccountByNumber(accountNumber);

		if (accounts == null || !accounts.getAc_password().equals(acPassword)) {
			logger.error("Validation errors: {}", result.getAllErrors());
			result.rejectValue("from_account", "error.from_account", "비밀번호가 일치하지 않습니다");
			model.addAttribute("accounts", accounts);

			List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
			model.addAttribute("codeOrganNames", codeOrganNames);
			return "auto/transferAuto";
		}

		try {
			// 로그인한 사용자의 user_num 설정
			autoBean.setUser_num(loginUserBean.getUser_num());

			// 자동이체 정보를 데이터베이스에 추가
			autoService.addAuto(autoBean);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + autoBean.getAuto_start());
			System.out.println("2@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + autoBean.getAuto_end());
		} catch (Exception e) {
			logger.error("Validation errors: {}", result.getAllErrors());
			e.printStackTrace();
			System.out.println("Exception : " + e);
			System.out.println("Exception : " + e.getMessage());

			return "auto/transferAuto";
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + autoBean.getAuto_start());
		System.out.println("2@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + autoBean.getAuto_end());
		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}

	// 수정 페이지
	@GetMapping("/transferAutoFix")
	public String TransferAutoFix(@RequestParam("auto_num") int auto_num, Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		AutoBean autoBean = autoService.getAutoByAutoNum(auto_num);
		int userNum = loginUserBean.getUser_num();
		List<AccountBean> accounts = accountService.getAccount(userNum);
		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();

		model.addAttribute("autoBean", autoBean);
		model.addAttribute("accounts", accounts);
		model.addAttribute("codeOrganNames", codeOrganNames);
		return "auto/transferAutoFix";
	}

	// 수정 API
	@PostMapping("/transferAutoFix")
	public String modifyTransferAutoFix(@ModelAttribute("autoBean") AutoBean autoBean,
			@RequestParam("ac_password") String acPassword, Model model, BindingResult result) {

		// 계좌 비밀번호 확인
		String accountNumber = autoBean.getFrom_account();
		AccountBean account = accountService.getAccountByNumber(accountNumber);

		if (account == null || !account.getAc_password().equals(acPassword)) {
			result.rejectValue("from_account", "error.from_account", "비밀번호가 일치하지 않습니다");
			int userNum = loginUserBean.getUser_num();
			List<AccountBean> accounts = accountService.getAccount(userNum);
			model.addAttribute("accounts", accounts);

			return "auto/transferAutoFix";
		}

		// 자동이체 정보 수정
		try {
			autoService.updateAuto(autoBean);
		} catch (ExceptionMessage e) {
			result.rejectValue("to_account", "error.to_account", e.getMessage());
			int userNum = loginUserBean.getUser_num();
			List<AccountBean> accounts = accountService.getAccount(userNum);
			model.addAttribute("accounts", accounts);
			return "auto/transferAutoFix";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			result.rejectValue("to_account", "error.to_account", "자동이체 수정 중 오류가 발생했습니다.");
			int userNum = loginUserBean.getUser_num();
			List<AccountBean> accounts = accountService.getAccount(userNum);
			model.addAttribute("accounts", accounts);
			return "auto/transferAutoFix";
		}

		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}

	// 삭제 API
	@PostMapping("/deleteTransferAuto")
	public String deleteTranferAuto(@RequestParam(name = "auto_num", required = true) int auto_num, Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		try {
			autoService.deleteAuto(auto_num);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			model.addAttribute("errorMessage", "자동이체 삭제 중 오류가 발생했습니다.");
			return "auto/transferAutoCheck";
		}

		// 자동이체 정보 삭제
		autoService.deleteAuto(auto_num);

		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}
}
