package com.msa2024.users;

import com.msa2024.entity.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.                                                    BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService  implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username = {}", username);
        MemberVO resultVO = userMapper.login(MemberVO.builder().member_id(username).build());
        if (resultVO == null) {
            log.info(username + " 사용자가 존재하지 않습니다");
            throw new UsernameNotFoundException(username + " 사용자가 존재하지 않습니다");
        }


        // 로그인 횟수 증가
        userMapper.loginCountInc(resultVO);

        return resultVO;
    }
    public MemberVO login(MemberVO memberVO) {
        // 데이터베이스에서 사용자 정보를 조회
        MemberVO resultVO = userMapper.login(memberVO);

        // BCryptPasswordEncoder를 사용한 비밀번호 검증
        if (resultVO != null && resultVO.isEqualsPwd()) {
            return resultVO;
        }
        return null;
    }
    public List<MemberVO> getUserList() {
        return userMapper.getUserList();
    }

    public MemberVO getUserById(String id) {
        return userMapper.getUserById(id);
    }

//    public MemberVO getUserByEmail(String email) {
//        return userMapper.getUserByEmail(email);
//    }

    public void insertUser(MemberVO userVo) { // 회원 가입
        if (!userVo.getUsername().equals("") && !userVo.getMember_id().equals("")) {
            // password는 암호화해서 DB에 저장
            userVo.setMember_pwd(passwordEncoder.encode(userVo.getPassword()));
            userMapper.insertUser(userVo);
        }
    }

    public void edit(MemberVO userVo) { // 회원 정보 수정
        // password는 암호화해서 DB에 저장
        userVo.setMember_pwd(passwordEncoder.encode(userVo.getPassword()));
        userMapper.updateUser(userVo);
    }

    public void withdraw(Long id) { // 회원 탈퇴
        userMapper.deleteUser(id);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
