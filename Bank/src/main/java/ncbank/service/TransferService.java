package ncbank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.CodeOrganBean;
import ncbank.beans.TransferBean;
import ncbank.beans.UserBean;
import ncbank.dao.TransferDAO;

@Service
public class TransferService {
	@Autowired
	private TransferDAO transferDAO;

	@Autowired
	private CodeOrganService codeOrganService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	public int getUserNum() {
		System.out.println(loginUserBean.getUser_num());
		return loginUserBean.getUser_num();
	}

	public void addTransfer(TransferBean transferBean) {
		// 출금 내역 추가
		transferBean.setTrans_type(2); // 출금
		transferDAO.addTransfer(transferBean);

		// 입금 내역 추가
		TransferBean depositBean = new TransferBean();
		depositBean.setTrans_type(1); // 입금
		depositBean.setTrans_balance(transferBean.getTrans_balance());
		depositBean.setTrans_text(transferBean.getTrans_text());
		depositBean.setFrom_account(transferBean.getTo_account());
		depositBean.setTo_account(transferBean.getFrom_account());
		depositBean.setCode_organ(transferBean.getCode_organ());
		transferDAO.addTransfer(depositBean);
	}

	public List<TransferBean> getTransfer(int userNum, String account) {
		List<TransferBean> transfers = transferDAO.getTransfer(userNum, account);
		List<CodeOrganBean> codeOrganBeans = codeOrganService.getCode_organ_name();

		for (TransferBean transfer : transfers) {
			String codeOrganCode = transfer.getCode_organ();
			for (CodeOrganBean codeOrgan : codeOrganBeans) {
				if (codeOrgan.getCode_organ().equals(codeOrganCode)) {
					transfer.setCode_organ_name(codeOrgan.getCode_organ_name());
					break;
				}
			}
		}

		return transfers;
	}

//	public void addTransfer(TransferBean transferBean) {
//		transferDAO.addTransfer(transferBean);
//
//		if ("005".equals(transferBean.getCode_organ())) {
//			// 입금
//			TransferBean depositBean = new TransferBean();
//			depositBean.setTrans_type(1);
//			depositBean.setTrans_balance(transferBean.getTrans_balance());
//			depositBean.setTrans_text(transferBean.getTrans_text());
//			depositBean.setFrom_account(transferBean.getTo_account());
//			depositBean.setTo_account(transferBean.getFrom_account());
//			depositBean.setCode_organ(transferBean.getCode_organ());
//			transferDAO.addTransfer(depositBean);
//		}
//
////		if ("005".equals(transferBean.getCode_organ())) {
////			transferDAO.addTransfer(depositBean);
////		} else {
////			depositBean.setFrom_account("외부 은행");
////			transferDAO.addTransfer(depositBean);
////		}
//	}

//	public List<TransferBean> getTransfer(int userNum, String account) {
//		List<TransferBean> transfers = transferDAO.getTransfer(userNum, account);
//		List<CodeOrganBean> codeOrganBeans = codeOrganService.getCode_organ_name();
//
//		for (TransferBean transfer : transfers) {
//			String codeOrganCode = transfer.getCode_organ();
//			for (CodeOrganBean codeOrgan : codeOrganBeans) {
//				if (codeOrgan.getCode_organ().equals(codeOrganCode)) {
//					transfer.setCode_organ_name(codeOrgan.getCode_organ_name());
//					break;
//				}
//			}
//		}
//
//		return transfers;
//	}
}
