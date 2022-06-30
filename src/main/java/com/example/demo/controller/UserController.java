package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.producer.SpringBootProducer;
import com.example.demo.service.IUserService;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.ResponseResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    private final String topic = "TestTopic";

    @Autowired
    IUserService iUserService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private SpringBootProducer producer;

    @RequestMapping("/sendMessage")
    public String sendMessage(String message) {
        producer.sendMessage(topic, message);
        return "消息发送完成";
    }

    //这个发送事务消息的例子中有很多问题，需要注意下。
    @RequestMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic, message);
        return "消息发送完成";
    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<List<User>> findAllUser() {
        List<User> findAllUser = (List<User>) redisUtils.get("findAllUser");
        if (CollectionUtils.isNotEmpty(findAllUser)) {
            return ResponseResult.makeOKRsp(findAllUser);
        }
        redisUtils.set("findAllUser", iUserService.list());
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

    @RequestMapping(value = "/saveUser", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseResult<Boolean> saveUser(@RequestBody User user) {
        if (Objects.isNull(user)) {
            return ResponseResult.makeErrRsp("请传入正确的参数！");
        }
        return ResponseResult.makeOKRsp(iUserService.save(user));
    }


    @GetMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        String filePath = "D:\\file\\" + "陕西_移动作业合同上传及电子签章接口.docx";
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

}
