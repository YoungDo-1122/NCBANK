package ncbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.CodeMoneyBean;
import ncbank.dao.CodeMoneyDAO;

@Service
public class CodeMoneyService {
	
	@Autowired
	private CodeMoneyDAO codeMoneyDAO;
	
	public List<CodeMoneyBean> getCodeMoneyList() {
		List<CodeMoneyBean> codeMoneyList = codeMoneyDAO.getCodeMoneyList();
		return codeMoneyList;
	}

}
