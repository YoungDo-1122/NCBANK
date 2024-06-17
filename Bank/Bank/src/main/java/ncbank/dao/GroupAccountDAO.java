package ncbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.GroupAccountBean;
import ncbank.beans.UserBean;
import ncbank.mapper.GroupAccountMapper;

@Repository
public class GroupAccountDAO {

	@Autowired
	private GroupAccountMapper groupAccountMapper;

	public List<AccountBean> selectAccountByUserNum(int user_num) {
		List<AccountBean> accountList = groupAccountMapper.selectAccountByUserNum(user_num);
		System.out.println("GroupAccountDAO - selectAccountByUserNum: " + user_num);
		System.out.println("GroupAccountDAO accountList : " + accountList);
		return accountList;
	}

	public List<AutoBean> getAccountInfo(String account, int user_num) {
        List<AutoBean> accountInfoList = groupAccountMapper.infoList(account, user_num);
        System.out.println("DAO accountInfoList: " + accountInfoList);
        return accountInfoList;
    }
	
	public AccountBean getAccountByAccountNumber(String account) {
        return groupAccountMapper.selectAccountByAccountNumber(account);
    }

}
