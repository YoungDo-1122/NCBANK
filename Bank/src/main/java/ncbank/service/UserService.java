package ncbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.UserBean;
import ncbank.dao.UserDAO;

// ���񽺸� �޴´� - �����͸� �����ͼ� �����۾��� �Ѵ�.

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO = null;
	
	// �Ѱܹ��� �ش� ���̵��� �̸��� ��� �������� �Ǵ� ����
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
