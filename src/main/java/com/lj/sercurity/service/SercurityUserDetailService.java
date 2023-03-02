package com.lj.sercurity.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lj.sercurity.mapper.UserMapper;
import com.lj.sercurity.model.SercurityUser;
import com.lj.sercurity.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SercurityUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username is: {}", username);

        // 查询用户信息
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUserName, username);
        User authUser = userMapper.selectOne(query);
        if(Objects.isNull(authUser)){
            throw new UsernameNotFoundException("the user is not found");
        }

        // TODO 授权
        //checkUserLock(loginRecord, allowAttemptCount);
        // 线上环境应该通过用户名查询数据库获取加密后的密码
        SercurityUser user = new SercurityUser(
            authUser.getId(),
            authUser.getUserName(),
            authUser.getNickName(),
            authUser.getPassword(),
            true,
            true,
            AuthorityUtils.NO_AUTHORITIES);
        return user;
    }
}
