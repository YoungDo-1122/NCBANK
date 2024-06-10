package ncbank.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginBean") UserBean tempLoginBean,
			@RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) {
		model.addAttribute("fail", fail); 
		return "user/login";
	}

	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginBean") UserBean tempLoginBean, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("===============");
			return "user/login";
		}

		// ���ǿ����� �ִ� �α��� ���� �ҷ�����
		userService.getLoginUserInfo(tempLoginBean);

		// loginUserBean : session ������ �ִ� ��, sessionScopre�� �ִ� UserBean�� ��ü
		// @Resource(name="loginUserBean") ���⸦ ���ؼ� isUserLogin() ����
		if (loginUserBean.isUserLogin() == true) {
			return "user/login_success";
		} else {

			return "user/login_fail";
		}
	}

	// @ModelAttribute("joinUserBean") : UserBean joinUserBean = new UserBean();
	// joinUserBean : getter setter �� �����ϰ� ����
	// ���� topMenu ���� �Ѿ������ ��ü�� ����� join���� �̵�
	@GetMapping("/join")
	public String join(@ModelAttribute("mBean") UserBean mBean) {
		return "user/join";
	}

	// post ���
	// ��ȿ�� �˻縦 �ϰ� �Ѿ�� ����
	// @Valid : �� ������̼��� �־�� UserBean ���� ��ȿ�� �˻縦 �ǽ� - �˻�����?
	// BindingResult : ��ȿ�� �˻��� �˻��� true/false
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("mBean") UserBean mBean, BindingResult memberResult) {
		// hasErrors() : �����ڵ尡 �ֳ�? ��ȿ�� �˻�� �ɷȴ�?
		/*
		 * ���� �˻��� �� ���� System.out.println(memberResult.hasErrors() + " " +
		 * memberResult.getFieldErrorCount()); List<ObjectError> temp =
		 * memberResult.getAllErrors();
		 * 
		 * for (ObjectError e : temp) { System.out.println(e.getDefaultMessage()); }
		 */

		if (memberResult.hasErrors()) {

			return "user/join";
		}

		userService.addUserInfo(mBean);

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
		loginUserBean.setUserLogin(false);
		return "user/logout";
	}

//    @InitBinder("mBean") // "mBean"�̶�� �̸��� �� �Ӽ����� �� ���δ��� ����
//    protected void initBinder(WebDataBinder binder) {
//    	UserValidator validator1 = new UserValidator();
//        binder.setValidator(validator1);
//    }

	// @InitBinder : ���ʿ� �̰� �а� ������
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);

	}

}
