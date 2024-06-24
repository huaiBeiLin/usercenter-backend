package com.example.usercenter.mapper;
import java.util.List;

import com.example.usercenter.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author hp
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-06-13 10:37:34
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllByUserAccount(@Param("userAccount") String userAccount);

    List<User> selectAllByUserAccountAndPassword(@Param("userAccount") String userAccount, @Param("password") String password);

    List<User> searchAllByUsername(@Param("username") String username);
}




