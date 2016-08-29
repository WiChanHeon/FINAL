package kr.spring.member.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import kr.spring.member.domain.MemberCommand;

@Repository("memberMapper")
public interface MemberMapper {
	@Insert("insert into smember(id,passwd,name,email,reg_date)values (#{id},#{passwd},#{name},#{email},sysdate)")
	public void insert(MemberCommand memberCommand);
	@Select("SELECT * FROM smember WHERE ID = #{id}")  
	public MemberCommand selectMember(String id);
	@Update("UPDATE smember SET name = #{name},passwd = #{passwd},email = #{email} WHERE ID = #{id}")
	public void update(MemberCommand member);
	@Delete("DELETE FROM smember WHERE id= #{id}")
	public void delete(String id);
	
}
