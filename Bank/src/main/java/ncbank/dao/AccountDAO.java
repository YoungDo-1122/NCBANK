package ncbank.dao;

import java.util.List;
import ncbank.beans.AccountBean;
import ncbank.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
	@Autowired
	private AccountMapper accountMapper;

	public List<AccountBean> getAccount(int userNum) {
		return accountMapper.getAccount(userNum);
	}
}
