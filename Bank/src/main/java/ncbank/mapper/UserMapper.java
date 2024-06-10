package ncbank.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import ncbank.beans.UserBean;

public interface UserMapper {

	// jsp���� ? �� ������ �� ���� ä�� -> MyBatis ���� #{} ���� �ٷ� �� ����
	@Select("SELECT COUNT(*) FROM login where id = #{id}")
	public int checkUserIdExist(String id);

//	// ȸ�� ���� (����)
//	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
//			"values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
//	public void addUserInfo(UserBean joinuserBean);

	@Select("SELECT COUNT(*) FROM login")
	public int userCount();

	@Insert("insert into member " + "(user_num  , name , address , phone , resident , email, join_date) "
			+ "values (#{user_num},#{name},#{address},#{phone},#{resident},#{email},#{join_date})")
	public void addMember(UserBean bean);

	@Insert("insert into login (user_num, id, pwd) values (#{user_num},#{id},#{pwd})")
	public void addLogin(UserBean bean);

	// �α��� �� ȸ������ ���� Ȯ��
	@Select("SELECT m.user_num, m.name " +
	        "FROM member m " +
	        "JOIN login l ON m.user_num = l.user_num " +
	        "WHERE l.id = #{id} AND l.pwd = #{pwd}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	

}
