package ncbank.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.UserBean;
import ncbank.dao.AutoDAO;
import ncbank.validator.ExceptionMessage;

@Service
public class AutoService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@Autowired
	private AutoDAO autoDAO;

	@Autowired
	private AccountService accountService;

	public void addAuto(AutoBean autoBean) throws ExceptionMessage {
		if (autoBean.getFrom_account().equals(autoBean.getTo_account())) {
			throw new ExceptionMessage("출금 계좌와 입금 계좌는 동일할 수 없습니다.");
		}

		// 내부 계좌와 외부 계좌 구분
		if ("005".equals(autoBean.getCode_organ())) {

			AccountBean toAccount = accountService.getAccountByNumber(autoBean.getTo_account());

			// Luhn 알고리즘 검사
			if (!accountService.isValidAccountNumber(autoBean.getTo_account())) {
				throw new ExceptionMessage("입금 계좌번호가 유효하지 않습니다.");
			}

		}
		autoBean.setAuto_start(new Date()); // 자동 이체 시작일을 현재 날짜로 설정
		autoDAO.addAuto(autoBean);
	}

	public List<AutoBean> getAuto(int user_num) {
		return autoDAO.getAuto(user_num);
	}

	public AutoBean getAutoByAutoNum(int auto_num) {
		return autoDAO.getAutoByAutoNum(auto_num);
	}

	public void updateAuto(AutoBean autoBean) throws ExceptionMessage {
		if (autoBean.getFrom_account().equals(autoBean.getTo_account())) {
			throw new ExceptionMessage("출금 계좌와 입금 계좌는 동일할 수 없습니다.");
		}

		// 내부 계좌와 외부 계좌 구분
		if ("005".equals(autoBean.getCode_organ())) {

			AccountBean toAccount = accountService.getAccountByNumber(autoBean.getTo_account());

			// Luhn 알고리즘 검사
			if (!accountService.isValidAccountNumber(autoBean.getTo_account())) {
				throw new ExceptionMessage("입금 계좌번호가 유효하지 않습니다.");
			}

		}

		autoDAO.updateAuto(autoBean);
	}

	public void deleteAuto(int auto_num) {
		autoDAO.deleteAuto(auto_num);
	}
}
