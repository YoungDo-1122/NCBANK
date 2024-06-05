package ncbank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.UserBean;
import ncbank.mapper.UserMapper;

@Repository
public class UserDAO {
	
	@Autowired
	private UserMapper userMapper;
	
	public String checkUserExist(String user_id) {
		return userMapper.checkUserIdExist(user_id);
	}
	
	// Mapper -> DAO -> Service�� �����ǰ� �Ȱ��� ��
	public void addUserInfo(UserBean joinUserBean) {
		System.out.println("UserDAO addUserInfo()"); // #test
		userMapper.addUserInfo(joinUserBean);
	}
	  
}
