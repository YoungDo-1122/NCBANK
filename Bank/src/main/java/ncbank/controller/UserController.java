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

// 동기식
@Controller
@RequestMapping("/user")
public class UserController {

	// 가공처리를 할땐 Service로 보냄
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

		// 세션영역에 있는 로그인 정보 불러오기
		userService.getLoginUserInfo(tempLoginBean);

		// loginUserBean : session 영역에 있는 거, sessionScopre에 있는 UserBean의 객체
		// @Resource(name="loginUserBean") 여기를 통해서 isUserLogin() 가능
		if (loginUserBean.isUserLogin() == true) {
			return "user/login_success";
		} else {

			return "user/login_fail";
		}
	}

	// @ModelAttribute("joinUserBean") : UserBean joinUserBean = new UserBean();
	// joinUserBean : getter setter 를 보유하고 있음
	// 최초 topMenu 에서 넘어왔을때 객체를 만들어 join으로 이동
	@GetMapping("/join")
	public String join(@ModelAttribute("mBean") UserBean mBean) {
		return "user/join";
	}

	// post 방식
	// 유효성 검사를 하고 넘어온 상태
	// @Valid : 이 어노테이션이 있어야 UserBean 에서 유효성 검사를 실시 - 검색엔진?
	// BindingResult : 유효성 검사의 검사결과 true/false
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("mBean") UserBean mBean, BindingResult memberResult) {
		// hasErrors() : 에러코드가 있냐? 유효성 검사게 걸렸니?
		/*
		 * 오류 검사할 떄 쓰기 System.out.println(memberResult.hasErrors() + " " +
		 * memberResult.getFieldErrorCount()); List<ObjectError> temp =
		 * memberResult.getAllErrors();
		 * 
		 * for (ObjectError e : temp) { System.out.println(e.getDefaultMessage()); }
		 */

		if (memberResult.hasErrors()) {

			return "user/join";
		}

		userService.addUserInfo(mBean);

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
		loginUserBean.setUserLogin(false);
		return "user/logout";
	}

//    @InitBinder("mBean") // "mBean"이라는 이름의 모델 속성에만 이 바인더를 적용
//    protected void initBinder(WebDataBinder binder) {
//    	UserValidator validator1 = new UserValidator();
//        binder.setValidator(validator1);
//    }

	// @InitBinder : 최초에 이걸 읽고 나가라
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);

	}

}
