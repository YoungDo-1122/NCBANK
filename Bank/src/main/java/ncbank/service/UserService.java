package ncbank.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.UserBean;
import ncbank.dao.UserDAO;
import ncbank.mapper.UserMapper;

// 서비스를 받는다 - 데이터를 가져와서 가공작업을 한다.
@Service
public class UserService {

	@Autowired
	private UserDAO userDaO;
	
	@Autowired
	private UserMapper userMapper;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */

	public boolean checkUserExist(String id) {
		return userDaO.checkUserExist(id);
	}

	public void addUserInfo(UserBean mBean) {
		int size = userMapper.userCount();
		mBean.setUser_num(size+1);
		userMapper.addMember(mBean);
		userMapper.addLogin(mBean);
		/*
		 * String hashedPassword = passwordEncoder.encode(mBean.getPwd());
		 * mBean.setPwd(hashedPassword);
		 */

		userDaO.addUserInfo(mBean);
	}

	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		
		return userMapper.getLoginUserInfo(tempLoginUserBean);

	}
}
