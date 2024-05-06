package com.msa2024.users;

import com.msa2024.entity.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    MemberVO login(MemberVO boardVO);
    int signup(MemberVO userVO);
    int updateMemberLastLogin(String email);
    MemberVO findMemberByUsername(String username);

    void loginCountInc(MemberVO member);

    //로그인 성공이 비밀 번호 틀린 회수를 초기화 함
    void loginCountClear(String email);

    void updateMember(MemberVO userVO);

    void unlockExpiredLockedAccounts();

    int checkDuplicateMemberId(MemberVO memberVO);
}
