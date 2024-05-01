package com.msa2024.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO implements UserDetails {

    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_address;
    private String member_detail_address;
    private String member_hand_phone;
    private String member_gender;
    private String member_birthday;
    private String member_authority;
    private String member_locked;
    private String member_roles;
    private String member_account_expired;
    private String member_account_locked;
    private int    member_login_count;
    private LocalDateTime member_last_login_time;
    private LocalDateTime lockoutTime;
    private List<GrantedAuthority> roles; // Use Spring Security's GrantedAuthority directly

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            roles = new ArrayList<>();
            Arrays.stream(member_roles.split(",")).forEach(role ->
                    roles.add(new SimpleGrantedAuthority("ROLE_" + role.trim()))
            );
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return member_pwd;
    }

    @Override
    public String getUsername() {
        return member_id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !"Y".equalsIgnoreCase(member_account_expired);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !"Y".equalsIgnoreCase(member_account_locked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You might want to add logic here if needed
    }

    @Override
    public boolean isEnabled() {
        return !"Y".equalsIgnoreCase(member_locked); // Assuming 'Y' means locked/disabled
    }

    public boolean isEqualsPwd() {
        return true;
    }
}
