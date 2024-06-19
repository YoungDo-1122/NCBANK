package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.AccountBean;
import ncbank.beans.UserBean;

public interface AccountMapper {

	@Select("select * from account where user_num = #{userNum}")
	List<AccountBean> getAccount(@Param("userNum") int userNum);

	@Select("select name from member where user_num = #{userNum}")
	UserBean getUserInfo(@Param("userNum") int userNum);

	@Insert("INSERT INTO account (account, ac_password, ac_balance, ac_type, ac_date, user_num) VALUES (#{account}, #{acPassword}, DEFAULT, #{acType}, SYSDATE, #{userNum})")
	void createAccount(AccountBean accountBean);
}
