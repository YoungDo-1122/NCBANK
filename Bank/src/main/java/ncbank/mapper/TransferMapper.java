package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ncbank.beans.TransferBean;

public interface TransferMapper {

	@Insert("INSERT INTO transfer (trans_num, trans_type, trans_money, trans_balance, trans_text, trans_date, from_account, to_account, code_organ) "
			+ "VALUES (SEQ_TRANSFER.NEXTVAL, #{trans_type}, #{trans_money}, #{trans_balance}, #{trans_text}, SYSDATE, #{from_account}, #{to_account}, #{code_organ})")
	public void addTransfer(TransferBean transferBean);

	@Select("<script>"
			+ "SELECT t.trans_type, t.trans_money, t.trans_balance, t.trans_text, t.trans_date, t.from_account, t.to_account, c.code_organ_name "
			+ "FROM transfer t " + "JOIN account a ON t.from_account = a.account "
			+ "JOIN login l ON a.user_num = l.user_num " + "LEFT JOIN code_organ c ON t.code_organ = c.code_organ "
			+ "WHERE l.user_num = #{userNum} " + "<if test='account != null'>AND t.from_account = #{account}</if>"
			+ "order by trans_date desc </script>")
	List<TransferBean> getTransfer(@Param("userNum") int userNum, @Param("account") String account);
}
