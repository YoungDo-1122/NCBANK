package ncbank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ncbank.beans.UserBean;
import ncbank.service.UserService;
import ncbank.validator.UserValidator;

// �����
@Controller
@RequestMapping("/user")
public class UserController {

	// ����ó���� �Ҷ� Service�� ����
	@Autowired
	UserService userService = null;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	// @ModelAttribute("joinUserBean") : UserBean joinUserBean = new UserBean();
	// joinUserBean : getter setter �� �����ϰ� ����
	// ���� topMenu ���� �Ѿ������ ��ü�� ����� join���� �̵�
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}

	// post ���
	// ��ȿ�� �˻縦 �ϰ� �Ѿ�� ����
	// @Valid : �� ������̼��� �־�� UserBean ���� ��ȿ�� �˻縦 �ǽ� - �˻�����?
	// BindingResult : ��ȿ�� �˻��� �˻��� true/false
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		// hasErrors() : �����ڵ尡 �ֳ�? ��ȿ�� �˻�� �ɷȴ�?
		if (result.hasErrors()) {
			return "user/join";
		}

		userService.addUserInfo(joinUserBean);

		// Ȥ�� �ٷ� login���� �Ѿ�� ����
		return "user/join_success";
	}

	@GetMapping("/index")
	public String index() {
		return "user/index";
	}

	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}

	@GetMapping("/logout")
	public String logout() {
		return "user/logout";
	}

	// @InitBinder : ���ʿ� �̰� �а� ������
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}

}
