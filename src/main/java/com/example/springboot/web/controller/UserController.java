package com.example.springboot.web.controller;

import com.example.springboot.model.master.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


/**
 * Desc ：User Controller
 * Created by JHAO on 2017/10/23.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public User createUser(User user) {
        userService.save(user);
        return user;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public User insert(User user) {
        userService.insert(user);
        return user;
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public User findUser(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<User> findAllUser() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/findAllByPage", method = RequestMethod.GET)
    public Iterable<User> findAllUserByPage(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "2") int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> users =  userService.findAllUsersByPage(pageable);
        return users.getContent();
    }

    @RequestMapping(value = "/findUsersByUserNmae", method = RequestMethod.GET)
    public Iterable<User> findUsersByUserNmae(@PageableDefault(value = 1, sort = {"id","userName"},direction = Sort.Direction.ASC) Pageable pageable, String userName) {
        return userService.findUsersByUserNamePage(pageable,userName);
    }
}
