package com.bs.firstboot.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.firstboot.model.dao.MemberDao;
import com.bs.firstboot.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberDao dao;
	private final SqlSession session;
	
	@Override
	public List<Member> selectMemberAll() {
		return dao.selectMemberAll(session);
	}

	@Override
	public Member selectById(String id) {
		return dao.selectMemberById(session,id);
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}

	@Override
	public int updateMember(Member m) {
		return dao.updateMember(session, m);
	}

	@Override
	public int deleteMember(String userId) {
		return dao.deleteMember(session, userId);
	}

}
