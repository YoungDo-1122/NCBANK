package ncbank.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ncbank.beans.UserBean;
import ncbank.dao.UserDAO;
import ncbank.mapper.UserMapper;
import ncbank.util.Encrypt;
import ncbank.util.SmsSender;

// 서비스를 받는다 - 데이터를 가져와서 가공작업을 한다.
@Service
public class UserService {

	@Autowired
	private UserDAO userDaO;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Encrypt encrypt;

	@Autowired
	private SmsSender smsSender;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@Value("${coolsms.apiKey")
	private String apiKey;

	@Value("${coolsms.apiSecret")
	private String apiSecret;

	public boolean checkUserExist(String id) {
		return userDaO.checkUserExist(id);
	}

	public void addUserInfo(UserBean mBean) {
		String salt = encrypt.getSalt();
		String encryptPasswd = encrypt.getEncrypt(mBean.getPwd(), salt);

		mBean.setPwd(encryptPasswd);
		mBean.setSalt(salt);

		userDaO.addUserInfo(mBean);
	}

	public void getLoginUserInfo(HttpServletRequest request, UserBean tempLoginUserBean) {

		UserBean dbUserBean = userDaO.getLoginUserInfo(tempLoginUserBean);
		if (dbUserBean != null) {
			String checkSalt = dbUserBean.getSalt(); // ㅅㅌ
			String checkpasswd = dbUserBean.getPwd(); // DB PWD
			String newPasswd = tempLoginUserBean.getPwd(); // newPasswd == 암호화전
			String pwd = encrypt.getEncrypt(newPasswd, checkSalt); // pwd -> newPwd 암호화한거
			System.out.println(checkpasswd);
			System.out.println(pwd);

			if (pwd.equals(checkpasswd)) {
				loginUserBean.setId(dbUserBean.getId());
				loginUserBean.setName(dbUserBean.getName());
				loginUserBean.setUser_num(dbUserBean.getUser_num());
				loginUserBean.setUserLogin(true);

				HttpSession session = request.getSession();
				session.setAttribute("loginUserBean", loginUserBean);

				System.out.println("Logged in user: " + loginUserBean.getId() + " - " + loginUserBean.getName() + " - " + loginUserBean.getUser_num());

			} else {
				loginUserBean.setUserLogin(false); // 로그인 실패
				System.out.println("Login failed. User not found.");
			}

		}
	}

	public String verificationCode(String phone) {

		String code = String.format("%06d", (int) (Math.random() * 900000));
		String text = ("[NC BANK] " + code);
		String result = smsSender.Smsvr(phone, text);

		if (result.equals("success")) {
			return code;
		} else {
			return "fail";
		}

	}

}
