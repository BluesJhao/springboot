package com.example.springboot.web.controller;

import com.example.springboot.dto.BaseResDTO;
import com.example.springboot.dto.RoleCreateReqDTO;
import com.example.springboot.model.slave.Role;
import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Desc ：Role Controller
 * Created by JHAO on 2017/10/24.
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public int insert(@Validated Role role, BindingResult bindingResult) {

//  web参数校验结果绑定BindingResult
//  if (bindingResult.hasErrors() == true) {
//            List<FieldError> list = bindingResult.getFieldErrors();
//            System.out.println(CollectionUtils.isEmpty(list)?
//                    "参数错误！":list.get(0).getDefaultMessage());
//        }
        return roleService.insert(role);
    }
    @RequestMapping(value = "/insertOne", method = RequestMethod.GET)
    public BaseResDTO insertOne(RoleCreateReqDTO role) {
        return roleService.insertRole(role);
    }

    @RequestMapping(value = "/selectOne/{primaryKey}", method = RequestMethod.GET)
    public Role selectOne(@PathVariable("primaryKey") int primaryKey) {
        return roleService.selectOne(primaryKey);
    }

    //Role中的多个属性值以AND拼接SQL
    @RequestMapping(value = "/selectRole", method = RequestMethod.GET)
    public List<Role> selectRole(Role role) {
        return roleService.selectByRole(role);
    }
}
