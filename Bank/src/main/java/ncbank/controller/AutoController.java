package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.CodeOrganBean;
import ncbank.beans.UserBean;
import ncbank.service.AccountService;
import ncbank.service.AutoService;
import ncbank.service.CodeOrganService;

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

	@PostMapping("/transferAuto") // 자동이체 등록 폼 제출을 처리하는 메소드 추가
	public String addTransferAuto(@ModelAttribute("autoBean") AutoBean autoBean, Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		// 로그인한 사용자의 user_num 설정
		autoBean.setUser_num(loginUserBean.getUser_num());

		// 자동이체 정보를 데이터베이스에 추가
		autoService.addAuto(autoBean);

		return "redirect:/auto/transferAutoCheck"; // 자동이체 조회 페이지로 리디렉션
	}

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
}
