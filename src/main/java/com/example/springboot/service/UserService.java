package com.example.springboot.service;

import com.example.springboot.mapper.second.UserMapper;
import com.example.springboot.model.master.User;
import com.example.springboot.repository.master.UserPagerRepository;
import com.example.springboot.repository.master.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Desc ：User Service
 * Created by JHAO on 2017/10/23.
 */
@Service
public class UserService {

    @Resource()
    private UserRepository userRepository;

    @Resource()
    private UserPagerRepository userPagerRepository;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Transactional(transactionManager = "master_transactionManager")
    public void save(User user){
        userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User findUserById(int id){
        return userRepository.findOne(id);
    }

    public User findUsersByUserName(String userName){
        return userRepository.findUsersByUserName(userName);
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Page<User> findAllUsersByPage(Pageable pageable){
        return userPagerRepository.findAll(pageable);
    }

    public Page<User> findUsersByUserNamePage(Pageable pageable, String userName){
        return userPagerRepository.findUsersByUserName(userName ,pageable);
    }
    //注：事务配置必须与对应数据源相匹配,此处需配置分布式事务
    @Transactional(transactionManager = "second_transactionManager",rollbackFor = Exception.class)
    public int insert(User user){
        //此处应提供分布式事务
        userMapper.insert(user);
        userRepository.save(user);
        System.out.println(1/0);
        return 0;
    }
}
