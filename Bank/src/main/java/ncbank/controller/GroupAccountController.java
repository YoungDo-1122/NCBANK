package ncbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groupAccount")
public class GroupAccountController {

	@GetMapping("/create")
	public String login() {
		return "groupAccount/groupAccountCreate";
	}
	
	@GetMapping("/about")
	public String join() {
		return "groupAccount/groupAccountAbout";
	}
	
	@GetMapping("/invite")
	public String modify() {
		return "groupAccount/groupAccountInvite";
	}
	
	@GetMapping("/management")
	public String logout() {
		return "groupAccount/groupAccountManagement";
	}
	
	
}
