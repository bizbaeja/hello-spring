package com.msa2024.users;

import com.msa2024.entity.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.                                                    BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Scheduled(fixedRate = 600000) // 600,000 milliseconds = 10 minutes
    public void unlockExpiredAccounts() {
        log.info("Running scheduled task to unlock expired locked accounts");
        userMapper.unlockExpiredLockedAccounts();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username = {}", username);
        MemberVO resultVO = userMapper.login(MemberVO.builder().member_id(username).build());
        if (resultVO == null) {
            log.info(username + " 사용자가 존재하지 않습니다");
            throw new UsernameNotFoundException(username + " 사용자가 존재하지 않습니다");
        }

        MemberVO user  = userMapper.findMemberByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "해당 유저를 찾을 수 없습니다.:");
        }
        return user;
//        // 사용자 입력 비밀번호 (예: 폼 입력을 통해 받은 비밀번호)
//        String rawPassword = "userInputPassword";  // 사용자 입력 비밀번호를 어떻게 받을지 구현 필요
//
//        // 데이터베이스에서 가져온 인코딩된 비밀번호
//        String encodedPassword = resultVO.getMember_pwd();
//        System.out.println(String.format("bcryptPasswordEncoder: %s", encodedPassword));

//        // 비밀번호 검증
//        if (!bcryptPasswordEncoder.matches(rawPassword, encodedPassword)) {
//            log.info("비밀번호가 일치하지 않습니다");
//            throw new UsernameNotFoundException("비밀번호가 일치하지 않습니다.");
//        }



    }
    public MemberVO login(MemberVO memberVO) {
        // 데이터베이스에서 사용자 정보를 조회
        MemberVO resultVO = userMapper.login(memberVO);

        // BCryptPasswordEncoder를 사용한 비밀번호 검증
        if (resultVO != null && resultVO.isEqualsPwd(resultVO.getMember_pwd())) {
            return resultVO;
        }
        return null;
    }

    public void insertUser(MemberVO userVO) {
        if (!userVO.getUsername().equals("") && !userVO.getMember_id().equals("")) {
            userVO.setMember_pwd(bcryptPasswordEncoder.encode(userVO.getPassword()));
            int result = userMapper.signup(userVO);
            if (result == 0) {
                throw new RuntimeException("User registration failed");
            }
        }
    }


    public  void edit (MemberVO userVO){
        userVO.setMember_pwd(bcryptPasswordEncoder.encode(userVO.getPassword()));
        userMapper.updateMember(userVO);
    }

}
