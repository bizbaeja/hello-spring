package com.msa2024.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO implements UserDetails {

    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_address;
    private String member_phone_number;
    private String member_gender;
    private String member_roles;
    private String member_account_expired;
    private String member_account_locked;
    private int    member_login_count;
    private LocalDateTime member_last_login_time;
    private LocalDateTime lockoutTime;
    private List<GrantedAuthority> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (member_roles == null || member_roles.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(member_roles.split(","))
                .map(String::trim)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return member_pwd;
    }

    @Override
    public String getUsername() {
        return member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return "N".equalsIgnoreCase(member_account_expired);
    }

    @Override
    public boolean isAccountNonLocked() {
        return "N".equalsIgnoreCase(member_account_locked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isEqualsPwd(String pwd) {
        return this.member_pwd.equals(pwd);
    }

}
