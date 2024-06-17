package ncbank.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class ContentBean {

	/*
	 * create table content_table ( content_idx number constraint CONTENT_PK primary
	 * key, content_subject varchar2(500) not null, content_text long not null,
	 * content_file varchar2(500), content_board_idx number not null constraint
	 * CONTENT_FK2 references board_info_table(board_info_idx), content_date date
	 * not null );
	 */

	private int content_idx; // 글 번호
	@NotBlank
	private String content_subject; // 글 제목
	@NotBlank
	private String content_text; // 글 내용
	private String content_file; // 첨부파일의 이름만 데이터베이스에 저장
	private MultipartFile upload_file; // 실제 브라우저에 띄울 파일
	private int content_writer_idx;
	private int content_board_idx; // 글 게시글의 번호 ex) 공지사항은 1번 , 새소식은 2번
	private String content_date;

	public int getContent_idx() {
		return content_idx;
	}

	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}

	public String getContent_subject() {
		return content_subject;
	}

	public void setContent_subject(String content_subject) {
		this.content_subject = content_subject;
	}

	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public String getContent_file() {
		return content_file;
	}

	public void setContent_file(String content_file) {
		this.content_file = content_file;
	}

	public MultipartFile getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}

	public int getContent_board_idx() {
		return content_board_idx;
	}

	public void setContent_board_idx(int content_board_idx) {
		this.content_board_idx = content_board_idx;
	}


	public String getContent_date() {
		return content_date;
	}

	public void setContent_date(String content_date) {
		this.content_date = content_date;
	}

	public int getContent_writer_idx() {
		return content_writer_idx;
	}

	public void setContent_writer_idx(int content_writer_idx) {
		this.content_writer_idx = content_writer_idx;
	}

}
