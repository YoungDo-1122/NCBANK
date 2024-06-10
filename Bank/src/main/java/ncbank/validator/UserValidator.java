package ncbank.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ncbank.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean loginBean = (UserBean) target;

		String beanName = errors.getObjectName();
		System.out.println(beanName); // joinUserBean

		if (beanName.equals("joinUserBean")) {

			if (!loginBean.getPwd().equals(loginBean.getPwd2())) {
				errors.rejectValue("pwd", "NotEquals");
				errors.rejectValue("pwd2", "NotEquals");
			}

			if (!loginBean.isIdExistCheck()) {
				errors.rejectValue("id", "DontCheckUserIdExist");
			}

		}

	}

}
