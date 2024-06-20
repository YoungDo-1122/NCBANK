package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeOrganBean;
import ncbank.beans.TransferBean;
import ncbank.beans.UserBean;
import ncbank.service.AccountService;
import ncbank.service.CodeOrganService;
import ncbank.service.TransferService;

@Controller
@RequestMapping("/trans")
public class TransferController {

	@Autowired
	private TransferService transferService;

	@Autowired
	private CodeOrganService codeOrganService;

	@Autowired
	private AccountService accountService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/transferCheck")
	public String TransferCheck(@RequestParam(name = "account", required = false) String account, Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		int userNum = loginUserBean.getUser_num();
		System.out.println("이체내역 계좌 : " + account);
		System.out.println("이체내역 회원 번호 : " + userNum);

		List<TransferBean> transfers;
		if (account != null && !account.isEmpty()) {
			transfers = transferService.getTransfer(userNum, account);
		} else {
			transfers = transferService.getTransfer(userNum, null);
		}

		model.addAttribute("transfers", transfers);

		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);

		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
		model.addAttribute("codeOrganNames", codeOrganNames);

		return "trans/transferCheck";
	}

	@GetMapping("/transfer")
	public String Transfer(Model model) {
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		int userNum = transferService.getUserNum();

		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);

		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
		model.addAttribute("codeOrganNames", codeOrganNames);

		model.addAttribute("accountBean", new AccountBean());
		model.addAttribute("transferBean", new TransferBean());

		return "trans/transfer";
	}

	@PostMapping("/check_password")
	public @ResponseBody boolean checkPassword(@RequestBody AccountBean accountBean) {
		List<AccountBean> accounts = accountService.getAccount(1);
		AccountBean fromAccount = accounts.stream()
				.filter(account -> account.getAccount().equals(accountBean.getAccount())).findFirst().orElse(null);

		return fromAccount != null && fromAccount.getAc_password().equals(accountBean.getAc_password());
	}

	@PostMapping("/transfer_pass")
	public String transferPass(@Valid @ModelAttribute("accountBean") AccountBean accountBean, Model model,
			BindingResult result) {
		List<AccountBean> accounts = accountService.getAccount(1);
		AccountBean fromAccount = accounts.stream()
				.filter(account -> account.getAccount().equals(accountBean.getAccount())).findFirst().orElse(null);

		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
		if (fromAccount != null && fromAccount.getAc_password().equals(accountBean.getAc_password())) {
			model.addAttribute("passwordMatch", true);
			model.addAttribute("transferBean", new TransferBean());
			model.addAttribute("fromAccount", fromAccount.getAccount());
			model.addAttribute("codeOrganNames", codeOrganService.getCode_organ_name());
		} else {
			model.addAttribute("passwordMismatch", true);
			result.rejectValue("ac_password", "error.ac_password", "비밀번호가 일치하지 않습니다");
			model.addAttribute("accounts", accounts);
			model.addAttribute("codeOrganNames", codeOrganService.getCode_organ_name());
		}
		return "trans/transfer";
	}

	@PostMapping("/transfer_confirm")
	public String transferConfirm(@Valid @ModelAttribute("transferBean") TransferBean transferBean, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("codeOrganNames", codeOrganService.getCode_organ_name());
			return "trans/transferDetails";
		}

		transferService.addTransfer(transferBean);
		return "redirect:/trans/transferCheck";
	}

}