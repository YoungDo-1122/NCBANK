package ncbank.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.UserBean;
import ncbank.dao.UserDAO;

// ���񽺸� �޴´� - �����͸� �����ͼ� �����۾��� �Ѵ�.
@Service
public class UserService {

	@Autowired
	private UserDAO userDaO;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */

	public boolean checkUserExist(String id) {
		return userDaO.checkUserExist(id);
	}

	public void addUserInfo(UserBean mBean) {

		/*
		 * String hashedPassword = passwordEncoder.encode(mBean.getPwd());
		 * mBean.setPwd(hashedPassword);
		 */

		userDaO.addUserInfo(mBean);
	}

	public void getLoginUserInfo(UserBean tempLoginUserBean) {

		UserBean tempLoginUserBean2 = userDaO.getLoginUserInfo(tempLoginUserBean);
		// ������ �����Ͱ� �ִٸ�
		if (tempLoginUserBean2 != null) {
			loginUserBean.setId(tempLoginUserBean2.getId());
			loginUserBean.setName(tempLoginUserBean2.getName());
			loginUserBean.setUserLogin(true); // �α��� ����
		}
	
	}
}
