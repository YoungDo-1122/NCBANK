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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//		System.out.println("이체내역 회원 번호 : " + userNum);

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

		int userNum = loginUserBean.getUser_num();
		List<AccountBean> accounts = accountService.getAccount(userNum);
		model.addAttribute("accounts", accounts);

		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
		model.addAttribute("codeOrganNames", codeOrganNames);

		model.addAttribute("accountBean", new AccountBean());
		model.addAttribute("transferBean", new TransferBean());
		return "trans/transfer";
	}

	@PostMapping("/transfer_confirm")
	public String transferConfirm(@Valid @ModelAttribute("transferBean") TransferBean transferBean,
			@RequestParam("ac_password") String acPassword, Model model, BindingResult result) {

		if (result.hasErrors()) {
			int userNum = transferService.getUserNum();
			List<AccountBean> accounts = accountService.getAccount(userNum);
			model.addAttribute("accounts", accounts);

			List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
			model.addAttribute("codeOrganNames", codeOrganNames);
			return "trans/transfer";
		}

		// 계좌 비밀번호 확인
		int userNum = transferService.getUserNum();
		List<AccountBean> accounts = accountService.getAccount(userNum);

		AccountBean fromAccount = accounts.stream()
				.filter(account -> account.getAccount().equals(transferBean.getFrom_account())).findFirst()
				.orElse(null);

		if (fromAccount == null || !fromAccount.getAc_password().equals(acPassword)) {
			result.rejectValue("from_account", "error.from_account", "비밀번호가 일치하지 않습니다");
			System.out.println("입력한 비밀번호 : " + acPassword);
			System.out.println("계좌 비밀번호 : " + fromAccount.getAc_password());
			model.addAttribute("accounts", accounts);

			List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
			model.addAttribute("codeOrganNames", codeOrganNames);
			return "trans/transfer";
		}
		// 출금 및 입금 내역 추가
//		transferBean.setTrans_type(-1); // 출금
		transferService.addTransfer(transferBean);
//
//		// 입금 내역 추가
//		TransferBean depositBean = new TransferBean();
//		depositBean.setTrans_type(1); // 입금
//		depositBean.setTrans_balance(transferBean.getTrans_balance());
//		depositBean.setTrans_text(transferBean.getTrans_text());
//		depositBean.setFrom_account(transferBean.getTo_account());
//		depositBean.setTo_account(transferBean.getFrom_account());
//		depositBean.setCode_organ(transferBean.getCode_organ());
//		transferService.addTransfer(depositBean);
		return "redirect:/trans/transferCheck";
	}

//	@PostMapping("/check_password")
//	public @ResponseBody boolean checkPassword(@RequestBody AccountBean accountBean) {
//		
//		int userNum = loginUserBean.getUser_num();
//		List<AccountBean> accounts = accountService.getAccount(userNum);
//		
//		AccountBean fromAccount = accounts.stream()
//				.filter(account -> account.getAccount().equals(accountBean.getAccount())).findFirst().orElse(null);
//		
//		return fromAccount != null && fromAccount.getAc_password().equals(accountBean.getAc_password());
//	}

//	@PostMapping("/transfer_confirm")
//	public String transferConfirm(@Valid @ModelAttribute("transferBean") TransferBean transferBean, Model model,
//			BindingResult result) {
//
//		if (result.hasErrors()) {
//			model.addAttribute("codeOrganNames", codeOrganService.getCode_organ_name());
//			return "trans/transferDetails";
//		}
//
//		transferService.addTransfer(transferBean);
//
//		return "redirect:/trans/transferCheck";
//	}

//	@PostMapping("/transfer_pass")
//	public String transferPass(@Valid @ModelAttribute("accountBean") AccountBean accountBean, Model model,
//			BindingResult result) {
//
//		int userNum = loginUserBean.getUser_num();
//		List<AccountBean> accounts = accountService.getAccount(userNum);
//
//		AccountBean fromAccount = accounts.stream()
//				.filter(account -> account.getAccount().equals(accountBean.getAccount())).findFirst().orElse(null);
//
//		List<CodeOrganBean> codeOrganNames = codeOrganService.getCode_organ_name();
//
//		if (fromAccount != null && fromAccount.getAc_password().equals(accountBean.getAc_password())) {
//			model.addAttribute("passwordMatch", true);
//			model.addAttribute("transferBean", new TransferBean());
//			model.addAttribute("fromAccount", fromAccount.getAccount());
//			model.addAttribute("codeOrganNames", codeOrganNames);
//		} else {
//			model.addAttribute("passwordMismatch", true);
//			result.rejectValue("ac_password", "error.ac_password", "비밀번호가 일치하지 않습니다");
//			model.addAttribute("accounts", accounts);
//			model.addAttribute("codeOrganNames", codeOrganNames);
//		}
//		model.addAttribute("accountBean", accountBean);
//
//		return "trans/transfer";
//	}

}