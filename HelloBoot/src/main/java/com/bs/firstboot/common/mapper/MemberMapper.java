package com.bs.firstboot.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bs.firstboot.model.dto.Member;

@Mapper // Mapper 기능을 하는 인터페이스
public interface MemberMapper {
	
//	@Results(id="",value = {
//			@Result(property= Column=),
//			@Result(property= Column=),
//			@Result(property= Column=),
//			@Result(property= Column=)
//	})
	

	@Select("SELECT * FROM MEMBER")
	List<Member> selectMemberAll();

	@Select("SELECT * FROM MEMBER WHERE USERID=#{userId}")
	Member selectMemberById(String userId);

	@Insert("INSERT INTO MEMBER" + "VALUES(#{userId},#{password},#{name},"
			+ "#{gender},#{age},#{email},#{phone},#{address},'코딩,운동',DEFAULT)")
	int insertMemebr(Member m);

	@Update("UPDATE MEMBER SET\r\n"
			+ "		USERNAME=#{name}, PASSWORD=#{password}, GENDER=#{gender}, AGE=#{age},"
			+ "		EMAIL=#{email}, PHONE=#{phone}, ADDRESS=#{address} WHERE\r\n"
			+ "		USERID=#{userId}")
	int updateMember(Member m);

	@Delete("DELETE FROM MEMBER"
			+ "		WHERE USERID=#{userId}")
	int deleteMember(String userId);

}
