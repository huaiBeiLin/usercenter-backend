package com.example.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.mapper.UserMapper;
import com.example.usercenter.service.UserService;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author hp
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-06-12 16:16:48
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private YuCongMingClient client;
    private static final String SALT = "xxx";

    private static final String prefix = "你是一个数据分析师，接下来我会给你我的分析目标和原始数据，请告诉我分析结论。";
    public long userRegister(String userAccount, String password, String checkPassword, String planetCode) {
        if (StringUtils.isAnyBlank(userAccount, password, checkPassword, planetCode)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (password.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }
        if (!password.equals(checkPassword)) {
            return -1;
        }
        if (planetCode.length() > 5) {
            return -1;
        }
        Pattern compile = Pattern.compile(".*[[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\n|\\r|\\t].*");
        Matcher matcher = compile.matcher(userAccount);
        if (matcher.matches()) {
            return -1;
        }
        long count = userMapper.selectAllByUserAccount(userAccount).size();
//        List<User> list = userMapper.selectAllByUserAccount(userAccount);
//        for (int i = 0; i < list.size(); i++)
//            System.out.println(list.get(i) + " ");
        if (count > 0) {
            return -1;
        }
        String entryPassword = DigestUtils.md5Hex(SALT + password);
        User user = new User();
        user.setUserAccount(userAccount);
        user.setPassword(entryPassword);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if (saveResult == false) {
            return -1;
        }
        return user.getId();
    }
    public User UserLogin(String userAccount, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, password)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (password.length() < 8) {
            return null;
        }
        Pattern compile = Pattern.compile(".*[[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\n|\\r|\\t].*");
        Matcher matcher = compile.matcher(userAccount);
        if (matcher.matches()) {
            return null;
        }
        String entryPassword = DigestUtils.md5Hex(SALT + password);
        QueryWrapper<User> mapperQuery = new QueryWrapper<User>();
        mapperQuery.eq("userAccount", userAccount);
        mapperQuery.eq("password", entryPassword);
        User user = userMapper.selectOne(mapperQuery);
        if (userMapper.selectAllByUserAccountAndPassword(userAccount, entryPassword).size() == 0) {
            log.info("user Login error, userAccount cannot match password");
            return null;
        }
        user = userMapper.selectAllByUserAccountAndPassword(userAccount, entryPassword).get(0);
        User safetyUser = getSafetyUser(user);
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    public List<User> searchUsers(String username) {
        List<User> list = new ArrayList<>();
        if (StringUtils.isNotBlank(username)) {
            list = userMapper.searchAllByUsername("%" + username + "%");
        }
        return list;
    }

    @Override
    public long logout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    public boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User)userObj;
        if (user == null || user.getUserRole() != 1) {
            return false;
        }
        return true;
    }


    public BaseResponse handleExcel(InputStream excelIO) throws IOException {
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(1651468516836098050L);
        devChatRequest.setMessage(excelIO.toString());
        BaseResponse<DevChatResponse> response = client.doChat(devChatRequest);
        return response;
    }

    public User getSafetyUser(User user) {
        User safetyUser = new User();
        safetyUser.setUsername(user.getUsername());
        safetyUser.setId(user.getId());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setGender(user.getGender());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        return safetyUser;
    }
}




