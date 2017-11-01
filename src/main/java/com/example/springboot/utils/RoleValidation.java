package com.example.springboot.utils;

import com.example.springboot.common.statusEnum.StatusEnum;

/**
 * Desc ：
 * Created by JHAO on 2017/10/26.
 */
public class RoleValidation {

    public static <T> StatusEnum validate(T t) {
        try {
            if (t == null) {
//                log.info("字段校验不通过:ValidationServer->validate|参数为空");
                return StatusEnum.CODE_9999;
            }
            String msg = ValidationUtlis.validate(t);
            if (msg != null) {
//                log.info("字段校验不通过:ValidationServer->validate|参数:{},错误:{}", FastJsonUtil.json2String(t),msg);
                return StatusEnum.CODE_9998;
            }
            return null;//基础字段校验通过
        } catch (Exception e) {
//            log.error("字段校验异常:ValidationServer->validate|"+ FastJsonUtil.json2String(t),e);
            throw e;
        }
    }
}
