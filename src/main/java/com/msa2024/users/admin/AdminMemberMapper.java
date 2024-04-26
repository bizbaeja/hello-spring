package com.msa2024.users.admin;

import com.msa2024.entity.MemberVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminMemberMapper {

	MemberVO login(MemberVO boardVO);

}
