package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IUserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
