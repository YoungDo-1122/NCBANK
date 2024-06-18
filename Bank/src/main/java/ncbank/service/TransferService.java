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
		transferDAO.addTransfer(transferBean);
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
}
