package ncbank.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import ncbank.beans.UserBean;

public interface UserMapper {

    // jsp에서 ? 로 설정한 뒤 값을 채움 -> MyBatis 에서 #{} 으로 바로 값 설정
    @Select("SELECT COUNT(*) FROM login where id = #{id}")
    public int checkUserIdExist(String id);

    // // 회원 가입 (저장)
    // @Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
    // "values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
    // public void addUserInfo(UserBean joinuserBean);

    @Select("SELECT COUNT(*) FROM login")
    public int userCount();

    // 회원가입
    @Insert("insert into member " + "(user_num  , name , address , phone , resident , email, join_date) "
            + "values (#{user_num},#{name},#{address},#{phone},#{resident},#{email},#{join_date})")
    public void addMember(UserBean bean);

    @Insert("insert into login (user_num, id, pwd, salt) values (#{user_num},#{id},#{pwd},#{salt})")
    public void addLogin(UserBean bean);

    // 로그인 시 회원가입 정보 확인
    @Select("SELECT m.user_num,m.name,m.phone,m.resident,m.join_date, l.id,l.pwd,l.salt" + "	from member m "
            + "	left join login l " + "	on m.user_num = l.user_num where l.id=#{id}")
    UserBean getLoginUserInfo(UserBean tempLoginUserBean);
    /*
     * SELECT m.user_num,m.name,m.address,m.phone,m.resident,m.email,m.join_date,
     * l.id,l.pwd from member m left join login l on m.user_num = l.user_num;
     * 
     * SELECT id,pwd,salt FROM login where id=#{id}
     */

}
