package com.msa2024.users;

import com.msa2024.entity.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    MemberVO login(MemberVO boardVO);

    int updateMemberLastLogin(String email);

    //로그인시 비밀 번호 틀린 회수를 1 증가 함
    //틀린 횟수가 5회 이상이면 계정을 잠근다
    void loginCountInc(MemberVO member);

    //로그인 성공이 비밀 번호 틀린 회수를 초기화 함
    void loginCountClear(String email);

}
