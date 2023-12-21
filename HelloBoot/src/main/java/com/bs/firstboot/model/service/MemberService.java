package com.bs.firstboot.model.service;

import java.util.List;

import com.bs.firstboot.model.dto.Member;

public interface MemberService {

	List<Member> selectMemberAll();
	
	Member selectById(String id);
	
	int insertMember(Member m);
	
	int updateMember(Member m);
	
	int deleteMember(String userId);
	
	
}
