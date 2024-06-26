//package com.msa2024.users;
//
//import com.msa2024.entity.MemberVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class AuthProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String uid = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//
//        PasswordEncoder passwordEncoder = userService.passwordEncoder();
//        UsernamePasswordAuthenticationToken token;
//        MemberVO memberVO = userService.getUserById(uid);
//
//
//
//        if (memberVO != null && passwordEncoder.matches(password, memberVO.getPassword())) {
//            List<GrantedAuthority> roles = new ArrayList<>();
//            roles.add(new SimpleGrantedAuthority("USER"));
//
//            token = new UsernamePasswordAuthenticationToken(memberVO.getMember_id(), null, roles);
//
//            return token;
//        }
//
//        throw new BadCredentialsException("No such user or wrong password.");
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return  true;
//    }
//}
