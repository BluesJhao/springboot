package com.example.springboot.mapper.primary;

import com.example.springboot.mapper.BasicMapper;
import com.example.springboot.model.slave.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Desc ：Role mapper
 * Created by JHAO on 2017/10/24.
 * don’t extends tk.mybatis.mapper.common.BaseMapper;
 */

//@Mapper
public interface RoleMapper extends BasicMapper<Role> {

    @Insert("insert into role(id,role_name) values(#{role.id},#{role.roleName})")
    int insertOrUpdate(@Param("role") Role role);
}
