package ncbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
//      return "index"; //WEB-INF/views/index.jsp - configureViewResolvers
		return "redirect:main"; // redirect: -> 주소매핑(주소재요청) get방식

	}

}
