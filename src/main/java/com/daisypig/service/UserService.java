package com.daisypig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daisypig.entity.User;

public interface UserService extends IService<User> {
    void addUserDetail(User user);

}