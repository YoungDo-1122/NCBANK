package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.GroupAccountBean;


public interface GroupAccountMapper {

    @Select("SELECT account, ac_name FROM account WHERE user_num = #{user_num}")
    List<AccountBean> selectAccountByUserNum(@Param("user_num") int user_num);

    @Select("SELECT a.auto_balance, a.auto_type, a.auto_next_date, a.auto_status, a.to_account, ac.ac_password, ac.ac_name "
            + "FROM auto a, account ac, member m "
            + "WHERE a.to_account = ac.account AND ac.user_num = m.user_num AND ac.account = #{account} AND m.user_num = #{user_num}")
    List<AutoBean> infoList(@Param("account") String account, @Param("user_num") int user_num);

    @Select("SELECT account, ac_name, ac_password FROM account WHERE account = #{account}")
    AccountBean selectAccountByAccountNumber(@Param("account") String account);

	/*
	 * @Insert("INSERT INTO groupinfo (group_num, user_num, group_joindate, group_leader, group_name) "
	 * +
	 * "VALUES (groupinfo_seq.nextval, #{user_num}, #{group_joindate}, #{group_leader}, #{group_name, jdbcType=VARCHAR})"
	 * )
	 * 
	 * @Options(useGeneratedKeys = true, keyProperty = "group_num", keyColumn =
	 * "group_num") void createGroupAccount(GroupAccountBean groupAccount);
	 */
    
    @Insert("INSERT INTO groupinfo (group_num, user_num, group_joindate, group_leader, group_name) " +
            "VALUES (groupinfo_seq.nextval, #{user_num}, #{group_joindate}, '1', #{group_name, jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "group_num", keyColumn = "group_num")
    void createGroupAccount(GroupAccountBean groupAccount);

    @Insert("INSERT INTO groupinfo (group_num, user_num, group_joindate, group_leader, group_name) " +
            "VALUES (#{group_num}, #{user_num}, #{group_joindate}, '0', #{group_name, jdbcType=VARCHAR})")
    void addMemberToGroup(GroupAccountBean groupAccount);
    

    @Select("SELECT group_leader, group_name FROM groupinfo WHERE group_num = #{group_num}")
    String selectGroupLeaderByGroupNum(@Param("group_num") int group_num);

    @Select("SELECT * FROM groupinfo WHERE group_num = #{group_num}")
    GroupAccountBean selectGroupAccountByGroupNum(@Param("group_num") int group_num);

    @Select("SELECT group_num FROM groupinfo WHERE user_num = #{user_num} ORDER BY group_joindate DESC FETCH FIRST 1 ROWS ONLY")
    Integer getLatestGroupNumByUser(@Param("user_num") int user_num);
}


