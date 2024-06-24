package com.example.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/*
 * @author Sunny
 * @description //TODO 
 * @createTime  ${DATE} ${TIME} 
 * @param  
 * @return 
        **/


@Data
public class UserLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -543286974259314538L;

    private String userAccount;

    private String password;
}
