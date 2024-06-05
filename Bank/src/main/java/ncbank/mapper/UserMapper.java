package ncbank.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.UserBean;

public interface UserMapper {
	
	// jsp에서 ? 로 설정한 뒤 값을 채움 -> MyBatis 에서 #{} 으로 바로 값 설정
	@Select("select user_name "+ "from user_table " + "where user_id = #{user_id}")
	public String checkUserIdExist(String user_id);
	
	// 회원 가입 (저장)
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
			"values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	public void addUserInfo(UserBean joinuserBean);
}
