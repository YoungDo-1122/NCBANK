package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;

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
	public String addTransferAuto(@ModelAttribute("autoBean") AutoBean autoBean,
			@RequestParam("ac_password") String acPassword, Model model, BindingResult result) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		// 계좌 비밀번호 확인
		String accountNumber = autoBean.getFrom_account();
		AccountBean accounts = accountService.getAccountByNumber(accountNumber);

		if (accounts == null || !accounts.getAc_password().equals(acPassword)) {
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
		} catch (Exception e) {

			System.out.println("Exception : " + e);

			return "auto/transferAuto";
		}

		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}

	// 수정 페이지
	@GetMapping("/transferAutoFix")
	public String TransferAutoFix(Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		int userNum = loginUserBean.getUser_num();
		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);
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
			e.printStackTrace();
		}

		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}

	// 삭제 페이지
	@GetMapping("/deleteTransferAuto")
	public String deleteTranferAuto() {
		return "auto/deleteTransferAuto";
	}

	// 삭제 API
	@PostMapping("/deleteTransferAuto")
	public String deleteTranferAuto(@RequestParam(name = "auto_num", required = false) int auto_num,
			@RequestParam(name = "ac_password", required = false) int acPassword, Model model, BindingResult result) {

		// 계좌 비밀번호 확인
		AutoBean auto = autoService.getAutoByAutoNum(auto_num);

		String accountNumber = auto.getFrom_account();
		AccountBean accounts = accountService.getAccountByNumber(accountNumber);

		if (accounts == null || !accounts.getAc_password().equals(acPassword)) {
			result.rejectValue("from_account", "error.from_account", "비밀번호가 일치하지 않습니다");

			int userNum = loginUserBean.getUser_num();
			List<AutoBean> autoList = autoService.getAuto(userNum);
			model.addAttribute("autoList", autoList);

			return "/auto/transferAutoCheck";
		}

		// 자동이체 정보 삭제
		autoService.deleteAuto(auto_num);

		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}
}
