package ncbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeOrganBean;
import ncbank.service.AccountService;
import ncbank.service.CodeOrganService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private CodeOrganService codeOrganService;

	@GetMapping("/accountCheck")
	public String AccountCheck(Model model) {
		List<AccountBean> accounts = accountService.getAccount(1);
		model.addAttribute("accounts", accounts);
		return "account/accountCheck";
	}

	@GetMapping("/accountCreate")
	public String AccountCreate() {
		return "account/accountCreate";
	}

	@GetMapping("/transfer")
	public String Transfer(Model model) {
		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
		model.addAttribute("codeOrganNames", codeOrganNames);
		return "account/transfer";
	}

	@GetMapping("/transferAuto")
	public String TransferAuto() {
		return "account/transferAuto";
	}

	@GetMapping("/transferAutoFix")
	public String TransferAutoFix() {
		return "account/transferAutoFix";
	}

	@GetMapping("/transferCheck")
	public String TransferCheck() {
		return "account/transferCheck";
	}
}