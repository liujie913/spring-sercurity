package com.lj.sercurity.controller;

import com.lj.sercurity.common.Response;
import com.lj.sercurity.dao.LoginService;
import com.lj.sercurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Response login(@RequestBody User user){

        return loginService.login(user);
    }
}
