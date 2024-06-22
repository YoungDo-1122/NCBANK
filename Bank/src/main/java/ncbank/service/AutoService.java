package ncbank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncbank.beans.AutoBean;
import ncbank.beans.UserBean;
import ncbank.dao.AutoDAO;

@Service
public class AutoService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@Autowired
	private AutoDAO autoDAO;

	public void addAuto(AutoBean autoBean) {
		autoDAO.addAuto(autoBean);
	}

	public List<AutoBean> getAuto(int user_num) {
		return autoDAO.getAuto(user_num);
	}

	public void updateAuto(AutoBean autoBean) {
		autoDAO.updateAuto(autoBean);
	}

	public void deleteAuto(int auto_num) {
		autoDAO.deleteAuto(auto_num);
	}
}
