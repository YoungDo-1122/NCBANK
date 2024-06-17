package ncbank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ncbank.beans.AccountBean;
import ncbank.beans.UserBean;
import ncbank.dao.GroupAccountDAO;
import ncbank.service.GroupAccountService;
import ncbank.service.UserService;

// 크롤링을 해서 얻어온 데이터를 Service에게 전달
// Rest : 비동기식 - 크롤링 해서 가져온 데이터를 이런식으로 사용
// 데이터를 바로 화면에 뿌려야한다 -> Rest 로 사용해야 함
@RestController
public class RestApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private GroupAccountDAO groupAccountDAO;

	@Autowired
	private GroupAccountService groupAccountService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	// 해당 경로로 가서 데이터를 가져와라.
	@GetMapping("/user/checkUserIdExist/{id}") // "/user/checkUserIdExist/?{user_id}" 와 같음
	public String checkUserIdExist(@PathVariable String id) {
		// @PathVariable : 주소에 직접 데이터 붙이기

		boolean check = userService.checkUserExist(id);

		// return check + "";
		// valueOf() : String 반환 근데 null 이면 "null" 문자열로 처리
		return String.valueOf(check); // Servlet 에서 jsp로 가게 설정해놓음
	}

	/*
	 * @GetMapping("/next") public @ResponseBody List<UserBean>
	 * selectAccount(UserBean logiUserBean) { loginUserBean.getUser_num();
	 * List<UserBean> accountList = groupAccountService.selectAccount(logiUserBean);
	 * System.out.println("accountList =  CTL" + accountList); return accountList; }
	 */

	/*
	 * @GetMapping("/next") public UserBean getUserNum(UserBean tempLoginBean, int
	 * user_num, Model model) { model.addAttribute("tempLoginBean", tempLoginBean);
	 * loginUserBean.setUser_num(tempLoginBean.getUser_num()); return
	 * groupAccountService.getUserNum(tempLoginBean); }
	 */

	/*
	 * @GetMapping("/next") public UserBean getUserNum(UserBean tempLoginUserBean,
	 * Model model) { model.addAttribute("tempLoginUserBean", tempLoginUserBean);
	 * tempLoginUserBean.setUser_num(loginUserBean.getUser_num());
	 * System.out.println(loginUserBean.getUser_num()); return
	 * groupAccountDAO.getUserNum(tempLoginUserBean); }
	 */

	/*
	 * @PostMapping("/next") public @ResponseBody List<AccountBean>
	 * selectAccount(int user_num) { int login_user_num =
	 * loginUserBean.getUser_num(); List<AccountBean> accountList =
	 * groupAccountService.selectAccount(login_user_num);
	 * System.out.println("Controller accountList = " + accountList); return
	 * accountList; }
	 */

}
