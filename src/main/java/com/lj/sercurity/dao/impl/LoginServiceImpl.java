package com.lj.sercurity.dao.impl;

import com.lj.sercurity.common.Response;
import com.lj.sercurity.common.util.JWTUtil;
import com.lj.sercurity.common.util.RedisService;
import com.lj.sercurity.dao.LoginService;
import com.lj.sercurity.model.SercurityUser;
import com.lj.sercurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    @Override
    public Response login(User user) {
        //AuthenticationManager authenticate进行认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证未通过，给出提示
        if(Objects.isNull(authenticate)){
            throw new UsernameNotFoundException("the user is not found");
        }

        SercurityUser loginUser = (SercurityUser)authenticate.getPrincipal();
        Long userId = loginUser.getUserId();
        // 认证通过，使用userid生成jwt
        String jwt = JWTUtil.createToken(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // redis存储完整用户信息 userid为key
        redisService.rank("login:" + userId, loginUser);
        Response response = Response.buildSuccess();
        response.setData(map);
        return response;
    }
}
