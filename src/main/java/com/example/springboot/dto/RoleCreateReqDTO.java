package com.example.springboot.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Desc ：
 * Created by JHAO on 2017/10/26.
 */
public class RoleCreateReqDTO {

    @NotNull(message = "Id not null")
    private String id;

    @Length(max = 12)
    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleCreateReqDTO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
