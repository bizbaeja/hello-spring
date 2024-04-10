package com.msa2024.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

        public UserVO login(UserVO userVO){
            UserVO resultVO = userMapper.login(userVO);
            if(resultVO != null && userVO.isEqualsPwd(resultVO.getPassword())){
                return resultVO;
            }
            return null;
        }

}
