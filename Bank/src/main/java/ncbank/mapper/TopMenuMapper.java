package ncbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ncbank.beans.BoardInfoBean;

// ���̺� ���� -> Bean (DTO) ���� -> Mapper�� Bean�� SQL ���� ������ ���
public interface TopMenuMapper {
	
	// @Select : ������ ���
	@Select("select board_info_idx, board_info_name " +
	         "from board_info_table " + "order by board_info_idx")
	List<BoardInfoBean> getTopMenuList();

}
