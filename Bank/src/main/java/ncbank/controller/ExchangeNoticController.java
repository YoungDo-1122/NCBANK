package ncbank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ncbank.beans.CodeMoneyBean;
import ncbank.beans.ExchangeNoticeBean;
import ncbank.beans.ExchangeRateBean;
import ncbank.beans.UserBean;
import ncbank.service.CodeMoneyService;
import ncbank.service.ExchangeAutoNoticeService;
import ncbank.service.ExchangeNoticeService;
import ncbank.service.ExchangeRateService;
import ncbank.utility.DateManager;
import ncbank.utility.EmailManager;
import ncbank.utility.ExchangeRateDTO;

@Controller
@RequestMapping("/exchange")
public class ExchangeNoticController {

	@Autowired
	private ExchangeNoticeService exchangeNoticeService;
	@Autowired
	private ExchangeRateService exchangeRateService;
	@Autowired
	private CodeMoneyService codeMoneyService;
	@Autowired
	private ExchangeAutoNoticeService autoNoticeService;

	@Autowired
	private DateManager dateManager;
	@Autowired
	private EmailManager emailManager;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	/* ==========[환율 알림]========== */
	// URL 패턴으로 구분
	// noticContentIndex - 1:안내 2:등록 3:변경 4:등록/변경 성공 5:삭제 성공
	private void addCommonAttributes(Model model) { // 공통적 사용 데이터 세팅
		List<CodeMoneyBean> codeMoneyList = codeMoneyService.getCodeMoneyList();
		if (null == codeMoneyList) {
			System.out.println("codeMoneyList null");
			return;
		}
		model.addAttribute("codeMoneyList", codeMoneyList);
	}

	// 기본 알림 페이지 - 알림 안내
	@GetMapping("/notice")
	public String notice(Model model) {
		System.out.println("ExchangeNoticController notice()");

		// 로그인 정보 test
		System.out.println("loginUserBean : " + loginUserBean);
		if (null != loginUserBean) {
			System.out.println("userNum : " + loginUserBean.getUser_num());
			System.out.println("isLogin : " + loginUserBean.isUserLogin());
		}

		return "exchange/notice";
	}

	// 알림 등록
	@GetMapping("noticeRegister")
	public String noticeRegister(@ModelAttribute("ExchangeNoticeBean") ExchangeNoticeBean exchangeNoticeBean,
			Model model) {
		System.out.println("ExchangeNoticController noticeRegister()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "exchange/not_login";
		}
		addCommonAttributes(model);

		// 기존 등록된 알림이 있다면 변경 페이지로
		ExchangeNoticeBean noticeBean = exchangeNoticeService.getExchangeRateNotice(loginUserBean.getUser_num());
		System.out.println("noticeBean : " + noticeBean);
		if (null != noticeBean) { // 이 유저가 신청한 알림 데이터가 있다면 변경창으로
			return noiticeEdit(noticeBean, model);
		}

		System.out.println("noticContentIndex : 2");
		model.addAttribute("noticContentIndex", 2);

		return notice(model);
	}

	@PostMapping("noticeRegisterPro")
	public String noticeRegisterPro(@ModelAttribute("ExchangeNoticeBean") ExchangeNoticeBean exchangeNoticeBean,
			Model model) {
		System.out.println("ExchangeNoticController noticeRegisterPro()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		System.out.println("userNum : " + loginUserBean.getUser_num());
		exchangeNoticeBean.setUser_num(loginUserBean.getUser_num());
		exchangeNoticeBean
				.setNotice_date(dateManager.parseStringToDate(dateManager.getCurrentDate("yyyyMMdd"), "yyyyMMdd"));
		exchangeNoticeService.addExchangeRateNotice(exchangeNoticeBean);

		autoNoticeService.addExchangeAutoNotice(loginUserBean.getUser_num());

		model.addAttribute("noticContentIndex", 4);
		return notice(model);
	}

	// 알림 변경
	@GetMapping("noticeEdit")
	public String noiticeEdit(@ModelAttribute("ExchangeNoticeBean") ExchangeNoticeBean exchangeNoticeBean,
			Model model) {
		System.out.println("ExchangeNoticController noiticeEdit()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		addCommonAttributes(model);

		ExchangeNoticeBean noticeBean = exchangeNoticeService.getExchangeRateNotice(loginUserBean.getUser_num());
		model.addAttribute("ExchangeNoticeBean", noticeBean);

		System.out.println("noticContentIndex : 3");
		model.addAttribute("noticContentIndex", 3);

		return notice(model);
	}

	@PostMapping("noticeEditPro")
	public String noticeEditPro(@ModelAttribute("ExchangeNoticeBean") ExchangeNoticeBean exchangeNoticeBean,
			Model model) {
		System.out.println("ExchangeNoticController noticeEditPro()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		exchangeNoticeService.updateExchangeRateNotice(exchangeNoticeBean);
		// 환율 알림 변경 시 자동알림의 state 를 변경해 다시 알림을 받을 준비를 한다
		autoNoticeService.updateExchangeAutoNotice(0, loginUserBean.getUser_num());

		return noiticeRegisterEditSuccess(model);
	}

	// 알림 삭제
	@GetMapping("noticeDeletPro")
	public String noticeDeletPro(Model model) {
		System.out.println("ExchangeNoticController noticeDeletPro()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		ExchangeNoticeBean noticeBean = exchangeNoticeService.getExchangeRateNotice(loginUserBean.getUser_num());
		model.addAttribute("ExchangeNoticeBean", noticeBean);

		exchangeNoticeService.deleteExchangeRateNotice(loginUserBean.getUser_num());
		// 환율 알림 삭제 시 해당 자동알림 정보도 삭제
		autoNoticeService.deleteExchangeAutoNotice(loginUserBean.getUser_num());

		return noticeDeletSuccess(model);
	}

	// success
	@GetMapping("noiticeRegisterEditSuccess")
	public String noiticeRegisterEditSuccess(Model model) {
		System.out.println("ExchangeNoticController noiticeRegisterEditSuccess()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}

		model.addAttribute("noticContentIndex", 4);

		return notice(model);
	}

	@GetMapping("noticeDeletSuccess")
	public String noticeDeletSuccess(Model model) {
		System.out.println("ExchangeNoticController noticeDeletSuccess()");
		if (null == loginUserBean || !loginUserBean.isUserLogin()) {
			return "user/not_login";
		}
		System.out.println("noticContentIndex : 5");
		model.addAttribute("noticContentIndex", 5);

		return notice(model);
	}

	// 메일전송
	@GetMapping("sendNoticeMail")
	// Spring MVC에서 자동으로 HttpServletRequest와 HttpServletResponse 객체를 컨트롤러 메서드에 주입
	public String sendNoticeMail(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ExchangeNoticController sendNoticeMail()");
		if (null == loginUserBean && !loginUserBean.isUserLogin()) {
			System.out.println("not login");
			return "user/rateInquiry";
		}
		/* jsp 내용 메일 */
		ExchangeNoticeBean exchangeNoticeBean = exchangeNoticeService
				.getExchangeRateNotice(loginUserBean.getUser_num());
		System.out.println("exchangeNoticeBean : " + exchangeNoticeBean);
		// 신청한 알림정보

		// Bean으로 빼보기
		String noticeDate = dateManager.parseDateToString(exchangeNoticeBean.getNotice_date(), "yyyy.MM.dd");

		request.setAttribute("ExchangeNoticeBean", exchangeNoticeBean);
		request.setAttribute("noticeDate", noticeDate);
		System.out.println("noticeDate : " + noticeDate);

		// 신청한 통화의 최근고지환율정보
		ExchangeRateBean rateBean = exchangeRateService.findFinalExchangeRate(exchangeNoticeBean.getCode_money());
		ExchangeRateDTO rateDTO = exchangeRateService.convertExchangeDTO(rateBean);
		String inquiryDate = dateManager.parseDateToString(rateDTO.getCode_date(), "yyyy.MM.dd");

		request.setAttribute("ExchangeRateBean", rateDTO);
		request.setAttribute("inquiryDate", inquiryDate);
		System.out.println("inquiryDate : " + inquiryDate);

		// 한국 원 정보는 따로 가져감
		ExchangeRateBean beanKRW = exchangeRateService.findFinalExchangeRate("KRW");
		ExchangeRateDTO dtoKRW = exchangeRateService.convertExchangeDTO(beanKRW);

		request.setAttribute("beanKRW", dtoKRW);

		/* 메일전송 */
		// 메일 보내는 기준 잡아야됨 -> Intercepter 에서?
		String rootPath = request.getServletContext().getRealPath("/");
		// inlinePath : 인라인 이미지 이름, imgPath : 실제 이미지 경로
		Map<String, String> inlinePathImgs = new HashMap<String, String>();

		inlinePathImgs.put("<Bank1>", rootPath + "resources/img/Bank1.png");
		inlinePathImgs.put("<Bank2>", rootPath + "resources/img/Bank2.png");
		inlinePathImgs.put("<Bank3>", rootPath + "resources/img/Bank3.jpg");

		emailManager.sendJspEmailWithInlineImage("jcjhjg12@gmail.com", "인라인 이미지 메일 테스트",
				"/WEB-INF/views/exchange/sendNoticeMail.jsp", inlinePathImgs, request, response);

		return "exchange/rateInquiry";
	}

} // class
