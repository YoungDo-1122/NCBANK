package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//	@GetMapping("/accountCheck")
//	public String AccountCheck(@RequestParam("userNum") int userNum, Model model) {
//
//		List<AccountBean> accounts = accountService.getAccount(userNum);
//		model.addAttribute("accounts", accounts);
//
//		return "account/accountCheck";
//	}

	@GetMapping("/accountCheck")
	public String AccountCheck(Model model) {
		int userNum = loginUserBean.getUser_num();
		System.out.println("회원 번호 : " + userNum);
		
		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);

		return "account/accountCheck";
	}

	@GetMapping("/accountCreate")
	public String AccountCreate() {

		return "account/accountCreate";
	}

	@GetMapping("/transferAuto")
	public String TransferAuto() {
		return "account/transferAuto";
	}

	@GetMapping("/transferAutoFix")
	public String TransferAutoFix() {
		return "account/transferAutoFix";
	}
}