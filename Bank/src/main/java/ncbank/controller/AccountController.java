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
import org.springframework.web.bind.annotation.RequestParam;

import ncbank.beans.AccountBean;
import ncbank.beans.UserBean;
import ncbank.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/accountCheck")
	public String AccountCheck(Model model) {
		int userNum = loginUserBean.getUser_num();
		System.out.println("계좌 조회 회원 번호 : " + userNum);

		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);

		return "account/accountCheck";
	}

	/*
	 * @GetMapping("/accountCreate") public String AccountCreate(Model model) { int
	 * userNum = loginUserBean.getUser_num(); System.out.println("계좌 생성 회원번호 : " +
	 * userNum);
	 * 
	 * UserBean users = accountService.getUserInfo(userNum);
	 * model.addAttribute("users", users); System.out.println("계좌 생성 회원명 : " +
	 * users.getName());
	 * 
	 * model.addAttribute("accountBean", new AccountBean());
	 * 
	 * return "account/accountCreate"; }
	 */

	@GetMapping("/accountCreate")
	public String AccountCreate(Model model) {
		int userNum = loginUserBean.getUser_num();
		System.out.println("계좌 생성 회원번호 : " + userNum);

		UserBean users = null;
		try {
			users = accountService.getUserInfo(userNum);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
			return "redirect:/errorPage";
		}

		model.addAttribute("users", users);
		model.addAttribute("accountBean", new AccountBean());

		return "account/accountCreate";
	}

	@PostMapping("/accountCreate")
	public String createAccount(@ModelAttribute AccountBean accountBean, @RequestParam("acPassword") String acPassword,
			@RequestParam("acPasswordConfirm") String acPasswordConfirm, Model model) {
		accountBean.setUser_num(loginUserBean.getUser_num());

		// 비밀번호와 비밀번호 확인 비교
		if (!acPassword.equals(acPasswordConfirm)) {
			model.addAttribute("errorMessage", "Passwords do not match.");
			return "account/accountCreate";
		}

		// 비밀번호 설정
		accountBean.setAc_password(acPassword);

		try {
			accountService.createAccount(accountBean);
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "account/accountCreate";
		}

		return "redirect:/account/accountCheck";
	}

	/*
	 * @PostMapping("/accountCreate") public String createAccount(@ModelAttribute
	 * AccountBean accountBean, Model model) {
	 * accountBean.setUser_num(loginUserBean.getUser_num());
	 * accountService.createAccount(accountBean); return
	 * "redirect:/account/accountCheck"; }
	 */

	@GetMapping("/transferAuto")
	public String TransferAuto() {
		return "account/transferAuto";
	}

	@GetMapping("/transferAutoFix")
	public String TransferAutoFix() {
		return "account/transferAutoFix";
	}
}