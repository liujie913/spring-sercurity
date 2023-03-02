package com.lj.sercurity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class SercurityUser extends User {

    private Long userId;
    private String principal;
    private String credential;
    private String realName;

    public SercurityUser(Long userId, String username, String realName, String password, boolean enabled,
                         boolean locked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, locked, authorities);
        this.userId = userId;
        this.realName = realName;
    }
}
