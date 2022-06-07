package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

    private Long id;

    private String password;
    private String userName;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(String userName, String password, Collection<? extends GrantedAuthority> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getAuthorities().stream().map(role ->
                new SimpleGrantedAuthority(role.getAuthority())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public static UserPrinciple buildFromEntity(UserEntity user) {


        return new UserPrinciple(user.getId(),
                user.getUserName(),
                user.getPassword(),
                null
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
