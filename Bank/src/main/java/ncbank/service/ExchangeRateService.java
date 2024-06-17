package ncbank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.ExchangeRateBean;
import ncbank.dao.ExchangeRateDAO;
import ncbank.utility.DateManager;
import ncbank.utility.ExchangeRateDTO;
import ncbank.utility.ExchangeRateUtility;

@Service
public class ExchangeRateService {

	@Autowired
	private ExchangeRateDAO exchangeRateDAO;
	@Autowired
	private ExchangeRateUtility exchangeRateUtils;

	@Autowired
	private DateManager dataManager;

	@Autowired
	private CodeMoneyService codeMoneyService;
	

	// DB에 저장되어 있는 데이터중 가장 최신의 환율 데이터 가져오기 (크롤링x)
	public List<ExchangeRateBean> getFinalExchangeRate() {
		System.out.println("CodeMoneyService getFinalExchangeRate()");
		List<ExchangeRateBean> finalExchangeRateList = exchangeRateDAO.getFinalExchangeRate();
		if (null == finalExchangeRateList || finalExchangeRateList.isEmpty()) {
			System.out.println("finalExchangeRateList error");
			return null;
		}
    	return finalExchangeRateList;
    }
	// 최신 환율 데이터중 원하는 동화의 데이터
    public ExchangeRateBean findFinalExchangeRate(String code_money) {
    	return exchangeRateDAO.findFinalExchangeRate(code_money);
    }
	
	// 현재 날짜 기준으로 최종환율고시일의 환율을 찾아옴 (DB탐색후 없으면 크롤링)
	public List<ExchangeRateBean> findFinalExchangeRate() {
		System.out.println("CodeMoneyService findFinalExchangeRate()");

		String exchangeRateDate = dataManager.getCurrentDate("yyyyMMdd");
		List<ExchangeRateBean> beanList = getExchangeRate(exchangeRateDate);

		int count = 0;
		while (null == beanList) {
			exchangeRateDate = dataManager.getMoveDate(exchangeRateDate, -1, "yyyyMMdd");
			beanList = getExchangeRate(exchangeRateDate);
			System.out.println("while count : " + (++count));
			System.out.println("exchangeRateDate : " + exchangeRateDate);
			if (10 < count) { // 무한루프 방지
				break;
			}
		}
		
		System.out.println("beanList : " + beanList);
		return beanList;
	}

	// 지정 날짜 환율정보를 DB에서 탐색해 가져옴 (탐색 후 없으면 크롤링)
	public List<ExchangeRateBean> getExchangeRate(String strDate) {
		System.out.println("getExchangeRate()");
		System.out.println("strDate : " + strDate);
		List<ExchangeRateBean> beanList = exchangeRateDAO.getExchangeRate(strDate);
		System.out.println("beanList : " + beanList);
		if (null == beanList || beanList.isEmpty()) { // DB에 등록된 환율정보가 없으면 크롤링
			// 매번크롤링하는거 좀 그런거같음 DB에 싹 저장하고 쓴다? -> 나중에 개선좀
			beanList = fetchExchangeRate(strDate);
		}

		return beanList;
	}

	// 지정 날짜 환율정보 크롤링 후 DB에 추가
	private List<ExchangeRateBean> fetchExchangeRate(String strDate) {
		System.out.println("fetchExchangeRate()");
		System.out.println("strDate : " + strDate);
		// 여기서 null 을 뱉으면 API에서 제공 불가능한 날짜의 환율
		List<ExchangeRateBean> beanList = exchangeRateUtils.fetchExchangeRateList(strDate);
		System.out.println("beanList : " + beanList);
		if (null == beanList || beanList.isEmpty()) { // 크롤링한 환율정보가 없을경우
			System.out.println("fetch result none");
			return null;
		}

		for (ExchangeRateBean bean : beanList) { // 환율정보를 DB추가
			exchangeRateDAO.addExchangeRate(bean);
		}
		return beanList;
	}
	
	// start ~ end 기간의 환율정보 DB에 추가 - 백 데이터 추가용
	public void addRateInquiry_DateRange(String startDate, String endDate) {
		System.out.println("CodeMoneyService insertTest");

		String exchangeRateDate = startDate;
		
		int count = 0;
		while (!dataManager.isDatesEqual(exchangeRateDate, endDate)) {
			List<ExchangeRateBean> beanList = getExchangeRate(exchangeRateDate);
			exchangeRateDate = dataManager.getMoveDate(exchangeRateDate, 1, "yyyyMMdd");
			System.out.println("while count : " + (++count));
			System.out.println("exchangeRateDate : " + exchangeRateDate);
			if (null == beanList) {
				System.out.println("not exchangeRate Date : " + exchangeRateDate);
			}
			if (1000 < count) { // 무한루프 방지
				break;
			}
		}
	}

	/* 환율정보Bean을 출력용 DTO로 전환 */
	// List<ExchangeRateBean> -> List<ExchangeRateDTO>
	public List<ExchangeRateDTO> convertExchangeDTOList(List<ExchangeRateBean> beanList) {
		System.out.println("ExchangeRateService convertExchangeDTOList()");
		if (null == beanList || beanList.isEmpty()) {
			System.out.println("beanList error");
			return null;
		}

		List<ExchangeRateDTO> rateDtoList = new ArrayList<>();

		for (ExchangeRateBean rateBean : beanList) {
			ExchangeRateDTO dto = new ExchangeRateDTO();
			exchangeBeanToDto(rateBean, dto);
			dto.setCode_money_name(codeMoneyService.getCodeMoneyName(rateBean.getCode_money()));
			rateDtoList.add(dto);
		}

		return rateDtoList;
	}
	
	// ExchangeRateBean -> ExchangeRateDTO
	public ExchangeRateDTO convertExchangeDTO(ExchangeRateBean rateBean) {
		System.out.println("ExchangeRateService convertExchangeDTO()");
		if (null == rateBean) {
			System.out.println("bean error");
			return null;
		}

		ExchangeRateDTO rateDto = new ExchangeRateDTO();
		
		exchangeBeanToDto(rateBean, rateDto);
		rateDto.setCode_money_name(codeMoneyService.getCodeMoneyName(rateBean.getCode_money()));
		
		return rateDto;
	}

	// ExchangeRateBean -> ExchangeRateDTO로 데이터 옮기기
	private void exchangeBeanToDto(ExchangeRateBean bean, ExchangeRateDTO dto) {
		dto.setCode_date(bean.getCode_date());
		dto.setCode_money(bean.getCode_money());
		dto.setEx_buy(bean.getEx_buy());
		dto.setEx_sell(bean.getEx_sell());
		dto.setEx_standard(bean.getEx_standard());
	}

}
