package com.example.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usercenter.Common.BaseResponse;
import com.example.usercenter.Common.ErrorCode;
import com.example.usercenter.Common.ResultUtils;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.model.domain.request.UserLoginRequest;
import com.example.usercenter.model.domain.request.UserRegisterRequest;
import com.example.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/user", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_NULL);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, password, checkPassword, planetCode)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        Long id = userService.userRegister(userAccount, password, checkPassword, planetCode);
        return ResultUtils.success(id);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(userAccount, password)) {
            return ResultUtils.error(ErrorCode.PARAMS_NULL);
        }
        User user = userService.UserLogin(userAccount, password, request);
        return ResultUtils.success(user);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(userService.USER_LOGIN_STATE);
        User user = (User)userObj;
        if (user == null) {
            return ResultUtils.error(ErrorCode.NOT_LOGIN);
        }
        if (user.getUserRole() != 1) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        return ResultUtils.success(userService.searchUsers(username));
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(long id, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        boolean res = userService.removeById(id);
        return ResultUtils.success(res);
    }

    @PostMapping("logout")
    public BaseResponse logout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        long id = userService.logout(request);
        return ResultUtils.success(id);
    }


}
