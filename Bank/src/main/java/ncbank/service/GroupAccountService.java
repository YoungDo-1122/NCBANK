package ncbank.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.GroupAccountBean;
import ncbank.beans.UserBean;
import ncbank.dao.GroupAccountDAO;
import ncbank.mapper.GroupAccountMapper;

@Service
public class GroupAccountService {

    @Autowired
    private GroupAccountDAO groupAccountDAO;

    @Autowired
    private GroupAccountMapper groupAccountMapper;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    public List<AccountBean> getAccountsByUserNum(int user_num) {
        List<AccountBean> accountList = groupAccountDAO.selectAccountByUserNum(user_num);
        System.out.println("GroupAccountService - getAccountsByUserNum: " + user_num);
        System.out.println("GroupAccountService - accountList : " + accountList);
        return accountList;
    }

    public List<AutoBean> infoList(String account, int user_num) {
    	List<AutoBean> accountInfoList = groupAccountDAO.getAccountInfo(account, user_num);
    	 System.out.println("GroupAccountService - infoList for account: " + account + ", user_num: " + user_num);
         System.out.println("GroupAccountService - accountInfoList: " + accountInfoList);
        return accountInfoList;
    }

    public AccountBean getAccountByAccountNumber(String account) {
        return groupAccountDAO.getAccountByAccountNumber(account);
    }

    public void createGroupAccount(GroupAccountBean groupAccount) throws SQLException {
        groupAccountMapper.createGroupAccount(groupAccount);
        System.out.println("Created Group Account with group_num: " + groupAccount.getGroup_num());
    }
    
    public void addMemberToGroup(GroupAccountBean groupAccount) throws SQLException {
        groupAccountMapper.addMemberToGroup(groupAccount);
    }
    
    

    public GroupAccountBean getGroupInfo(int group_num) {
        return groupAccountMapper.selectGroupAccountByGroupNum(group_num);
    }

    public Integer getLatestGroupNumByUser(int user_num) {
        return groupAccountMapper.getLatestGroupNumByUser(user_num);
    }
}


