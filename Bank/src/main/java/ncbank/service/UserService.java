package ncbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.UserBean;
import ncbank.dao.UserDAO;

// 서비스를 받는다 - 데이터를 가져와서 가공작업을 한다.

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO = null;
	
	// 넘겨받은 해당 아이디의 이름이 사용 가능한지 판단 여부
	public boolean checkUserExist(String user_id) {
		String user_name = userDAO.checkUserExist(user_id);
		// user_name.isEmpty();
		if (null != user_name) { 
			return false;
		}
		return true;
	}
	
	public void addUserInfo(UserBean joinUserBean) {
		userDAO.addUserInfo(joinUserBean);
	}
	
}
