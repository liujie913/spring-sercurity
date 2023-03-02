package com.lj.sercurity.dao;

import com.lj.sercurity.common.Response;
import com.lj.sercurity.model.User;

public interface LoginService {
    Response login(User user);
}
