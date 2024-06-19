package ncbank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AccountBean;
import ncbank.beans.UserBean;
import ncbank.dao.AccountDAO;

@Service
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	private static final String codeOrgan = "005";

	private static final int userAccountLength = 8;

	public int getUserNum() {
		System.out.println("AccountService userNum : " + loginUserBean.getUser_num());
		return loginUserBean.getUser_num();
	}

	public List<AccountBean> getAccount(int userNum) {
		return accountDAO.getAccount(userNum);
	}

	public UserBean getUserInfo(int userNum) {
		return accountDAO.getUserInfo(userNum);
	}

	// 실제 회원가입 시 사용되는 함수
	public String generateAccountNumberForNewMember(int userNum, int sequenceNumber) {
		return generateAccountNumber(userNum, sequenceNumber);
	}

	// 계좌번호 생성
	public void createAccount(AccountBean accountBean) {
		String accountNumber = generateAccountNumber(accountBean.getUser_num(), 1);
		accountBean.setAccount(accountNumber);
		accountDAO.createAccount(accountBean);
	}

	// Luhn 알고리즘을 사용하여 계좌번호에 검증 숫자를 추가하는 함수
	private String generateAccountNumber(int userNum, int sequenceNumber) {
		// 은행코드 + 회원번호 + 일련번호
		String baseAccountNumber = codeOrgan + formatMemberNumber(userNum) + sequenceNumber;

		// Luhn 알고리즘으로 검증 숫자 계산
		int checkDigit = calculateLuhnCheckDigit(baseAccountNumber);

		// 계좌번호에 검증 숫자 추가하여 반환
		return baseAccountNumber + checkDigit;
	}

	// 회원번호를 8자리로 포맷팅하는 함수 (앞을 0으로 채움)
	private String formatMemberNumber(int userNum) {
		return String.format("%0" + userAccountLength + "d", userNum);
	}

	// Luhn 알고리즘으로 검증 숫자 계산하는 함수
	private int calculateLuhnCheckDigit(String baseNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = baseNumber.length() - 1; i >= 0; i--) {
			int digit = Integer.parseInt(String.valueOf(baseNumber.charAt(i)));
			if (alternate) {
				digit *= 2;
				if (digit > 9) {
					digit -= 9;
				}
			}
			sum += digit;
			alternate = !alternate;
		}
		return (sum % 10 == 0) ? 0 : (10 - (sum % 10));
	}
}
