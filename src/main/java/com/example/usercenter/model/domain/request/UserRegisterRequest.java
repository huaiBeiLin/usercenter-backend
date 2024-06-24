package com.example.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3253980956799016230L;

    private String userAccount;

    private String password;

    private String checkPassword;

    private String planetCode;
}
