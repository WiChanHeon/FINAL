package w.board.domain;

import kr.spring.util.DurationFromNow;

public class DonanBoardReplyCommand {
	private int re_no;
	private String re_content;
	private String re_date;
	private int seq;
	private String id;
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = DurationFromNow.getTimeDiffLabel(re_date);
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BoardReplyCommand [re_no=" + re_no + ", re_content=" + re_content + ", re_date=" + re_date + ", seq="
				+ seq + ", id=" + id + "]";
	}
	
}
