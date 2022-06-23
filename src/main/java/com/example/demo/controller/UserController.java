package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.utils.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;

/**
 * <p>
 * 用户中心-用户信息表 前端控制器
 * </p>
 *
 * @author zm
 * @since 2022-06-22
 */
@RestController
@RequestMapping("/demo/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/findAllUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<List<User>> findAllUser() {
        return ResponseResult.makeOKRsp(iUserService.list());
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult page(@RequestBody String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageSize = jsonObject.getIntValue("pageSize");
        String name = jsonObject.getString("name");
        IPage<User> page = new Page<>(pageNum, pageSize);
        IPage<User> page1 = iUserService.page(page, new LambdaQueryWrapper<User>()
                // 主要演示这里可以加条件。在name不为空的时候执行
                .eq(StringUtils.isNotEmpty(name), User::getName, name));

        return ResponseResult.makeOKRsp(page1);
    }

}
