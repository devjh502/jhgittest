package com.bs.firstboot.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bs.firstboot.model.dto.Member;

public interface MemberDao {
	
	List<Member> selectMemberAll(SqlSession session);
	
	Member selectMemberById(SqlSession session, String id);
	
	int insertMember(SqlSession session, Member m);
	
	int updateMember(SqlSession sesison, Member m);
	
	int deleteMember(SqlSession session, String userId);
}
