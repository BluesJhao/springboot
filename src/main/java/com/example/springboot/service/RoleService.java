package com.example.springboot.service;

import com.example.springboot.common.statusEnum.StatusEnum;
import com.example.springboot.dto.BaseResDTO;
import com.example.springboot.dto.RoleCreateReqDTO;
import com.example.springboot.mapper.primary.RoleMapper;
import com.example.springboot.model.slave.Role;
import com.example.springboot.repository.slave.RoleRepository;
import com.example.springboot.utils.RoleValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Desc ：Role Service
 * Created by JHAO on 2017/10/24.
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleRepository roleRepository;

    //注：事务配置必须与对应数据源相匹配,与应用框架保持一致性
    @Transactional(transactionManager = "primary_transactionManager")
    public BaseResDTO insertRole(RoleCreateReqDTO role) {
        //basic validatation
        StatusEnum statusEnum = RoleValidation.validate(role);
        Role role1 = new Role();
        if (statusEnum != null && statusEnum != StatusEnum.getByCode("0000")) {
            //log.info().......
            return new BaseResDTO(statusEnum);
        }
        //biz validatation
        BeanUtils.copyProperties(role, role1);
        System.out.println(roleMapper.insert(role1));
        return new BaseResDTO<Role>(StatusEnum.CODE_0000);
    }
    public int insert(Role role) {
        return roleMapper.insert(role);
    }
    public Role selectOne(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }
    public List<Role> selectByRole(Role role) {
        return roleMapper.select(role);
    }
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
