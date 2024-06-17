package ncbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeBankBean;
import ncbank.beans.ExchangeBean;
import ncbank.dao.ExchangeDao;

@Service
public class ExchangeService {
	
	@Autowired
    private ExchangeDao exchangeDao;
	
	// api 데이터 exchange에 저장된거 가져오기.
    public List<ExchangeBean> getExchangeList(String cuDate) {
    	
    	
    	List<ExchangeBean> exchangeAskList = exchangeDao.getExchangeList(cuDate);
    		
        return exchangeAskList;

    }
    
    // code_bank - code_bank_name 엮은 데이터 가져오기
    public List<CodeBankBean> getCodeBankName(){
    	List<CodeBankBean> codeBankNameList = exchangeDao.getCodeBankName();
    	return codeBankNameList;
    }
    
    // user_name이랑 account 데이터 가져오기
    public List<AccountBean> getAccount(int user_num){
    	List<AccountBean> getAccountList = exchangeDao.getAccount(user_num);
    	return getAccountList;
    }
    
    //ajax
    

    public List<CodeBankBean> searchBankByKeyword(String keyword) {
        System.out.println("검색어 서비스: " + keyword);
        try {
            List<CodeBankBean> results = exchangeDao.searchBankByKeyword(keyword);
            System.out.println("검색 결과 서비스: " + results);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;  // 예외를 다시 던져서 상위 레이어에서 처리
        }
    }
    
 
}
