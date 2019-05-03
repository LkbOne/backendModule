package com.example.phoebe.youtiao;

import com.example.hugh.life.api.UserService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.user.AddUserArg;
import javafx.application.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Ignore
public class UserTests {
    @Autowired
    UserService userService;

    @Test
    public void addUserTest(){
        AddUserArg arg = new AddUserArg();
        arg.setAccount("lkb");
        arg.setName("Hugh");
        arg.setPassword("life");
        ModelResult reuslt = userService.addUser(arg);
        System.out.println();
    }
}
