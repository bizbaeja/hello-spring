package com.msa2024.users.admin;

import com.msa2024.entity.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminMemberMapper {

	MemberVO findByUsername(String username);
	void updateLoginAttempts(MemberVO user);
	List<MemberVO> findLockedAccounts();

	List<MemberVO> findAllMembers();

	MemberVO findById(String memberId);

	void updateMemberLockStatus(MemberVO member);

	void deleteMemberById(String memberId);
}

