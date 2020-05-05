package com.cpf.veadsool.controller;


import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.cpf.veadsool.entity.StudentFiles;
import com.cpf.veadsool.entity.User;
import com.cpf.veadsool.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基本信息表 前端控制器
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User userSelect = iUserService.login(user);
        return ErrorConstant.getSuccessResult(userSelect, "登陆成功");
    }
}
