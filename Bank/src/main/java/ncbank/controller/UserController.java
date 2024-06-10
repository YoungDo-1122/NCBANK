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

// 동기식
@Controller
@RequestMapping("/user")
public class UserController {

	// 가공처리를 할땐 Service로 보냄
	@Autowired
	UserService userService = null;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	// @ModelAttribute("joinUserBean") : UserBean joinUserBean = new UserBean();
	// joinUserBean : getter setter 를 보유하고 있음
	// 최초 topMenu 에서 넘어왔을때 객체를 만들어 join으로 이동
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}

	// post 방식
	// 유효성 검사를 하고 넘어온 상태
	// @Valid : 이 어노테이션이 있어야 UserBean 에서 유효성 검사를 실시 - 검색엔진?
	// BindingResult : 유효성 검사의 검사결과 true/false
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		// hasErrors() : 에러코드가 있냐? 유효성 검사게 걸렸니?
		if (result.hasErrors()) {
			return "user/join";
		}

		userService.addUserInfo(joinUserBean);

		// 혹은 바로 login으로 넘어가도 무방
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

	// @InitBinder : 최초에 이걸 읽고 나가라
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}

}
