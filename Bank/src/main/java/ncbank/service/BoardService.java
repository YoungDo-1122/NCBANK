package ncbank.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ncbank.beans.ContentBean;
import ncbank.beans.PageBean;
import ncbank.beans.UserBean;
import ncbank.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	@Value("${path.upload}")
	private String path_upload;

	@Value("${page.listcnt}")
	private int page_listcnt;

	@Value("${page.paginationcnt}")
	private int page_paginationcnt;

	@Autowired
	private BoardDao boardDao;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	private String saveUploadFile(MultipartFile upload_file) {

		String file_name = System.currentTimeMillis() + "_"
				+ FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "."
				+ FilenameUtils.getExtension(upload_file.getOriginalFilename());

		try {// 경로와 파일네임
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file_name;
	}

	public void addContentInfo(ContentBean writeContentBean) {

		MultipartFile upload_file = writeContentBean.getUpload_file();

		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println(file_name);
			writeContentBean.setContent_file(file_name); // 오라클에 파일 이름 저장
		}

		writeContentBean.setContent_writer_idx(loginUserBean.getUser_num());

		boardDao.addContentInfo(writeContentBean);

	}

	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}

	public List<ContentBean> getContentList(int board_info_idx, int page) {
		/*
		 * 1-> 0 2-> 10 3-> 20
		 */
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt); // 0부터 10개

		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}
	
	//페이지 관련
	public PageBean getContentCnt(int content_board_idx,int currentPage) {
		int contentCnt = boardDao.getContentCnt(content_board_idx);
		
		PageBean pageBean = new PageBean(contentCnt, currentPage, page_listcnt, page_paginationcnt);
		return pageBean;
	}



	/*
	// 이미지 처리 때문에 서비스 들어와야 함
	public void modifyContentInfo(ContentBean modifyContentBean) {

		MultipartFile uploadFile = modifyContentBean.getUpload_file();

		if (uploadFile.getSize() > 0) {
			String fileName = saveUploadFile(uploadFile);
			modifyContentBean.setContent_file(fileName);
		}

		
	}
	*/

}
