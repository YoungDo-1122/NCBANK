package ncbank.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ncbank.beans.AccountBean;
import ncbank.beans.CodeBankBean;
import ncbank.beans.ExchangeBean;
import ncbank.mapper.ExchangeMapper;

@Repository
public class ExchangeDao {
	
	@Autowired
    private ExchangeMapper exchangeMapper;
	
	// api 데이터 exchange에 저장된거 가져오기.
    public List<ExchangeBean> getExchangeList(String cuDate) {
    	
    		
        	List<ExchangeBean> exchangeAskList = exchangeMapper.getExchangeList(cuDate);
	    	return exchangeAskList;

    }
    
    // code_bank - code_bank_name 엮은 데이터 가져오기
    public List<CodeBankBean> getCodeBankName(){
    	List<CodeBankBean> codeBankNameList = exchangeMapper.getCodeBankName();
    	return codeBankNameList;
    }
    
    // user_name이랑 account 데이터 가져오기
    public List<AccountBean> getAccount(int user_num){
    	List<AccountBean> getAccountList = exchangeMapper.getAccount(user_num);
    	return getAccountList;
    }
    
    //ajax

    
    public List<CodeBankBean> searchBankByKeyword(String keyword) {
        try {
            return exchangeMapper.searchBankByKeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 예외 출력
            return new ArrayList<>();  // 빈 리스트 반환하여 클라이언트에 에러를 전송하지 않음
        }
    }
    
   
    
}
