package kr.spring.member.domain;

import java.sql.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberCommand {
	@NotEmpty
	private String id;
	//메시지 설정은 리소스 번들의 메시지가 우선
	@Size(min=4,max=10,message="비밀번호는 필수입니다.")
	private String passwd;
	@NotEmpty
	private String name;
	@Email
	@NotEmpty
	private String email;
	private Date reg_date;
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd){
		if(passwd.equals(userPasswd)){
			return true;
		}
		return false;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "MemberCommand [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + ", reg_date="
				+ reg_date + "]";
	}
	
	
}
