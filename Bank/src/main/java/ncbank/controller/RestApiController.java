package ncbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ncbank.service.UserService;

// ũ�Ѹ��� �ؼ� ���� �����͸� Service���� ����
// Rest : �񵿱�� - ũ�Ѹ� �ؼ� ������ �����͸� �̷������� ���
// �����͸� �ٷ� ȭ�鿡 �ѷ����Ѵ� -> Rest �� ����ؾ� ��
@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService = null;
	
	// �ش� ��η� ���� �����͸� �����Ͷ�.
	@GetMapping("/user/checkUserIdExist/{id}") // "/user/checkUserIdExist/?{user_id}" �� ����
	public String checkUserIdExist(@PathVariable String id) {
		// @PathVariable : �ּҿ� ���� ������ ���̱�
		
		boolean check = userService.checkUserExist(id);
		
		// return check + "";
		// valueOf() : String ��ȯ �ٵ� null �̸� "null" ���ڿ��� ó��
		return String.valueOf(check); // Servlet ���� jsp�� ���� �����س���
	}
	
	
}
