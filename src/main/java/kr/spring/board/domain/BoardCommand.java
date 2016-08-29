package kr.spring.board.domain;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
 
public class BoardCommand {
	private int seq;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private int hit;
	private Date regdate;
	private MultipartFile upload;
	private String filename;
	@NotEmpty
	private String id;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BoardCommand [seq=" + seq + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regdate="
				+ regdate + ", upload=" + upload + ", filename=" + filename + ", id=" + id + "]";
	}
	
	
}
