package ncbank.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.UserBean;
import ncbank.dao.UserDAO;

// 서비스를 받는다 - 데이터를 가져와서 가공작업을 한다.
@Service
public class UserService {

	@Autowired
	private UserDAO userDaO;
<<<<<<< Updated upstream
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Encrypt encrypt;
	
	@Autowired
	private SmsSender smsSender;
=======
>>>>>>> Stashed changes

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */

	public boolean checkUserExist(String id) {
		return userDaO.checkUserExist(id);
	}

	public void addUserInfo(UserBean mBean) {
<<<<<<< Updated upstream
		int size = userMapper.userCount();
		mBean.setUser_num(size+1);
		userMapper.addMember(mBean);
		userMapper.addLogin(mBean);
		/*
		 * String hashedPassword = passwordEncoder.encode(mBean.getPwd());
		 * mBean.setPwd(hashedPassword);
		 */
		String salt = encrypt.getSalt();
		String encryptPasswd = encrypt.getEncrypt(mBean.getPwd(), salt);
=======
>>>>>>> Stashed changes

		/*
		 * String hashedPassword = passwordEncoder.encode(mBean.getPwd());
		 * mBean.setPwd(hashedPassword);
		 */

		userDaO.addUserInfo(mBean);
	}

	public void getLoginUserInfo(UserBean tempLoginUserBean) {

<<<<<<< Updated upstream
		UserBean dbUserBean = userDaO.getLoginUserInfo(tempLoginUserBean);
		if (dbUserBean != null) {
			
			String checkSalt = dbUserBean.getSalt(); //ㅅㅌ
			String checkpasswd = dbUserBean.getPwd(); //DB PWD

			String newPasswd = tempLoginUserBean.getPwd(); //newPasswd == 암호화전

			String pwd = encrypt.getEncrypt(newPasswd, checkSalt); //pwd -> newPwd 암호화한거
			System.out.println(checkpasswd);
			System.out.println(pwd);
			
			
			if (pwd.equals(checkpasswd)) {
				loginUserBean.setId(dbUserBean.getId());
				loginUserBean.setName(dbUserBean.getName());
				loginUserBean.setUserLogin(true);

				HttpSession session = request.getSession();
				session.setAttribute("loginUserBean", loginUserBean);

				System.out.println("Logged in user: " + loginUserBean.getId() + " - " + loginUserBean.getName());
				
			} else {
				loginUserBean.setUserLogin(false); // 로그인 실패
				System.out.println("Login failed. User not found.");
			}

		}
	}
	
	public String verificationCode(String phone) {
		
		String code = String.format("%06d", (int)(Math.random() * 900000));
    	String text = ("[NC BANK] " + code);
		String result = smsSender.Smsvr(phone, text);
		
		if(result.equals("success")) {
			return code;
		}else {
			return "fail";
=======
		UserBean tempLoginUserBean2 = userDaO.getLoginUserInfo(tempLoginUserBean);
		// 가져온 데이터가 있다면
		if (tempLoginUserBean2 != null) {
			loginUserBean.setId(tempLoginUserBean2.getId());
			loginUserBean.setName(tempLoginUserBean2.getName());
			loginUserBean.setUser_num(tempLoginUserBean2.getUser_num());
			loginUserBean.setUserLogin(true); // 로그인 상태
>>>>>>> Stashed changes
		}
		
		
	}
}
