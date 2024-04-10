package com.msa2024.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    private String userid;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
    private String birth;
    private String gender;
    private String register;
    private List<String> hobbies; // 사용자의 취미 ID 목록
    private List<HobbyVO> hobbyList; // 사용자의 취미 ID 목록
    private String hobbyid; // 또는 적절한 데이터 타입
    public boolean isEqualsPwd(String pwd) {
        return this.password.equals(pwd);
    }

}