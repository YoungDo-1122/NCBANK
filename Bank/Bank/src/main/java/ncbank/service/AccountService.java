package ncbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AccountBean;
import ncbank.dao.AccountDAO;

@Service
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;

	public List<AccountBean> getAccount(int userNum) {
		return accountDAO.getAccount(userNum);
	}
}
