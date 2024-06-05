package ncbank.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.UserBean;

public interface UserMapper {
	
	// jsp���� ? �� ������ �� ���� ä�� -> MyBatis ���� #{} ���� �ٷ� �� ����
	@Select("select user_name "+ "from user_table " + "where user_id = #{user_id}")
	public String checkUserIdExist(String user_id);
	
	// ȸ�� ���� (����)
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
			"values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	public void addUserInfo(UserBean joinuserBean);
}
