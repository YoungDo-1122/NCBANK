package ncbank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AccountBean;
import ncbank.beans.UserBean;
import ncbank.dao.AccountDAO;

@Service
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	public int getUserNum() {
		System.out.println(loginUserBean.getUser_num());
		return loginUserBean.getUser_num();
	}

	public List<AccountBean> getAccount(int userNum) {
		return accountDAO.getAccount(userNum);
	}
}
