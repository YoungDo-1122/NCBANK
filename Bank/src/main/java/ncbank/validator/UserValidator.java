package ncbank.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ncbank.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// UserBean �� clazz ���� ��ӵǾ����� ������ Ŭ�������� �˻� | true, false ��ȯ
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target;

		if (!userBean.getUser_pw().equals(userBean.getUser_pw2())) {
			errors.rejectValue("user_pw", "NotEquals");
			errors.rejectValue("user_pw2", "NotEquals");
		}
		if (false == userBean.isUserIdExist()) {
			errors.rejectValue("user_id", "DontCheckUserIdExist");
		}
		
	} // validate

}
