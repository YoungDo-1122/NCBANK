package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ncbank.beans.UserBean;
import ncbank.service.UserService;
import ncbank.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginBean") UserBean tempLoginBean,
			@RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) {
		model.addAttribute("fail", fail);
		return "user/login";
	}

	@PostMapping("/login_pro")
	public String login_pro(HttpServletRequest request, @Valid @ModelAttribute("tempLoginBean") UserBean tempLoginBean,
			Model model, BindingResult result) {
		System.out.println(tempLoginBean);
		System.out.println(request);
		if (result.hasErrors()) {
			return "user/login";
		}

		userService.getLoginUserInfo(request, tempLoginBean);

		if (loginUserBean.isUserLogin()) {
			model.addAttribute("tempLoginBean", tempLoginBean);
			return "user/login_success";
		} else {
			model.addAttribute("fail", true);
			return "user/login_fail";
		}
	}

	@GetMapping("/join")
	public String join(@ModelAttribute("mBean") UserBean mBean) {
		return "user/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("mBean") UserBean mBean, BindingResult memberResult,Model model) {

		if (memberResult.hasErrors()) {
			memberResult.getFieldErrorCount();
			List<ObjectError> temp = memberResult.getAllErrors();
			for (ObjectError e : temp) {
				System.out.println(e.getDefaultMessage());
			}
			return "user/join";
		}
		if (!userService.canRegister(mBean.getPhone(), mBean.getResident())) {
			model.addAttribute("errorMessage", "이미 가입되어 있는 전화번호나 주민번호가 존재합니다");
			return "user/join";
		}

		String address = (mBean.getAdd2() != null && !mBean.getAdd2().isEmpty()) ? mBean.getAdd2() : mBean.getAdd3();
		mBean.setAddress(address);

		userService.addUserInfo(mBean);

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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}

	// 문자인증
	@PostMapping("/memberPhoneCheck")
	public @ResponseBody String memberPhoneCheck(@RequestParam(value = "to") String to) {
		System.out.println("------------------------------");
		String code = String.format("%06d", (int) (Math.random() * 900000));
		String text = ("[NC BANK] " + code);
		System.out.println(text);
		return code;
//      실제로 쓸때는 위에꺼 주석 처리하고 밑에꺼 써야댐
//    	String vr = userService.verificationCode(to);
//    	return vr;
	}
}
