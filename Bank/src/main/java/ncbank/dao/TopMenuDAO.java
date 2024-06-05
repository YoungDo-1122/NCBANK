package ncbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.BoardInfoBean;
import ncbank.mapper.TopMenuMapper;

// @Repository : Spring�� �ش� Ŭ������ ������Ʈ ��ĵ �������� �ڵ����� �����ϰ�, Spring �����̳ʿ� ������ ����Ѵ�.
@Repository
public class TopMenuDAO {
	
	// Jpa ������ Mapper�� ����� ������
	// Bean �� SQL ���ǿ����� ����� �س��ٸ� @Autowired�� TopMenuMapper �������̽��� ����ü�� ���Թ���
	@Autowired
	private TopMenuMapper topMenuMapper;
	
	
	public List<BoardInfoBean> getTopMenuList() {
		// TopMenuMapper ���� @Select �� ����� �������� ����� �޾ƿ´�.
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();	
		
		return topMenuList; // selelct�� ������� �Ѿ��
	}
	
}
