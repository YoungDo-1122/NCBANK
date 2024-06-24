package ncbank.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import ncbank.beans.ContentBean;
import ncbank.beans.UserBean;
import ncbank.service.BoardService;

public class CheckWriterinterceptor implements HandlerInterceptor {

	private UserBean loginUserBean;
	private BoardService boardService;

	public CheckWriterinterceptor(UserBean loginUserBean, BoardService boardService) {
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String str1 = request.getParameter("content_idx");
		int content_ixd = Integer.parseInt(str1);

		ContentBean currentContentBean = boardService.getContentInfo(content_ixd);

		if (currentContentBean.getContent_writer_idx() != loginUserBean.getUser_num()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;
		}
		return true;
	}
}
