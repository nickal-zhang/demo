package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户中心-用户信息表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2022-06-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findAllUser();

}
