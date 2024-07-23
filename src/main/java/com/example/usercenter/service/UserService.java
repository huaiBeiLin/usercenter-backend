package com.example.usercenter.service;

import com.example.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yucongming.dev.common.BaseResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
* @author hp
* @description 针对表【user】的数据库操作Service
* @createDate 2024-06-12 16:16:48
*/
@Service
public interface UserService extends IService<User> {
    public static final String USER_LOGIN_STATE = "userLoginState";

    long userRegister(String userAccount, String password, String checkPassword, String planetCode);
    User UserLogin(String userAccount, String password, HttpServletRequest request);

    List<User> searchUsers(String username);

    long logout(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    BaseResponse handleExcel(InputStream excelIO) throws IOException;

    User getSafetyUser(User user);
}
