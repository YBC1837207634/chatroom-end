package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.example.dto.UserDto;
import com.example.entity.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JWTUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> login(User user, HttpServletRequest request) {
        Integer userId = userService.isLogin(user);
        if (userId!=null) {
            UserDto userDto = new UserDto();
            User u = userService.getById(userId);
            BeanUtils.copyProperties(u, userDto);
            String token = JWTUtils.createToken(userId.toString(), u.getName());
            HttpSession session = request.getSession();
            session.setAttribute("name", u.getName());
            userDto.setToken(token);
            return Result.success(JSON.toJSONString(userDto));
        }
        return Result.error("登陆失败：账号或密码错误！");
    }
    
}
