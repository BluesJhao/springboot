package com.example.springboot.service;

import com.example.springboot.model.master.User;
import com.example.springboot.model.slave.Role;
import com.example.springboot.repository.master.UserRepository;
import com.example.springboot.utils.MailUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Desc ：
 * Created by JHAO on 2017/11/1.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource(name="userRepository")
    private UserRepository userRepository;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void inserUserTest(){
        User user = new User();
        user.setUserName("Test1");
        user.setPassword("Test1");
        userRepository.save(user);
    }

    @Test
    public void inserRoleTest(){
        Role role = new Role();
        role.setId("TT");
        role.setRoleName("Test1");
        roleService.save(role);
    }

    @Test
    public void sendMail(){
        String MAIL_MODEL_ADDRESS= "exception_mail";
        Assert.assertEquals(mailUtil.send(MAIL_MODEL_ADDRESS,new HashMap<>(),new String[]{"lijiahao@gomeholdings.com"},"Test"),true);
    }

}
