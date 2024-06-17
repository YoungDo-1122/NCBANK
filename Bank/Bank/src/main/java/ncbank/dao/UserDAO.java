package ncbank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.UserBean;
import ncbank.mapper.UserMapper;

@Repository
public class UserDAO {

	@Autowired
	private UserMapper userMapper;

	public boolean checkUserExist(String id) {
		return (0 == userMapper.checkUserIdExist(id));
	}

	// Mapper -> DAO -> Service로 가도되고 안가도 됨
	public void addUserInfo(UserBean mBean) {
		int size = userMapper.userCount();
		mBean.setUser_num(size + 1);
		userMapper.addMember(mBean);
		userMapper.addLogin(mBean);
	}

	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		return userMapper.getLoginUserInfo(tempLoginUserBean);
	}

}