package com.example.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private UserService userService;

//    @Test
//    public void testAddUser() {
//        User user = new User();
//        user.setUsername("auxin");
//        user.setUserAccount("auxin");
//        user.setAvatarUrl("https://cn.bing.com/images/search?view=detailV2&ccid=AgKTDA49&id=BF3EE6176A95DA4ED5CED2DC7B11BA0456EE3001&thid=OIP.AgKTDA494BFTM4byd35QuQHaHk&mediaurl=https%3a%2f%2fa.1stdibscdn.com%2ftiffany-co-1981-angela-cummings-leaves-necklace-earrings-suite-14kt-three-gold-for-sale-picture-7%2fj_26902%2fj_162897321656800512381%2fIMG_6521_master.jpg%3fwidth%3d768&exph=785&expw=768&q=threegold&simid=608015224964666996&FORM=IRPRST&ck=08AA61F1544D3BB1EB706D96B86F4836&selectedIndex=6&itb=0");
//        user.setGender(0);
//        user.setPassword("xxx");
//        user.setPhone("123");
//        user.setEmail("456");
//        boolean result = userService.save(user);
//        System.out.println(user.getId());
//        Assert.assertTrue(result);
//    }

    @Test
    public void test() {
        String userAccount = "auxin";
        String password = "";
        String checkPassword = "123456";
        String planetCode = "1";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
        userAccount = "au";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
        userAccount = "auxin";
        password = "123456";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
        userAccount = "au xi";
        password = "12345678";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
        checkPassword = "123456789";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
        userAccount = "dogauxin";
        checkPassword = "12345678";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
        userAccount = "auxin";
        Assert.assertEquals(-1, userService.userRegister(userAccount , password, checkPassword, planetCode));
    }

}