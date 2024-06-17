package ncbank.mapper;

import java.util.List;
import ncbank.beans.AccountBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {

	@Select("select * from account where user_num = #{userNum}")
	List<AccountBean> getAccount(@Param("userNum") int userNum);
}
