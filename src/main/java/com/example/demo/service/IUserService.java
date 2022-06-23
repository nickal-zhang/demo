package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 用户中心-用户信息表 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2022-06-22
 */
public interface IUserService extends IService<User> {

    List<User> findAllUser();

}
