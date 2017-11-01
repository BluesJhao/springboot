package com.example.springboot.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Desc ：Mybatis basic mapper
 * Created by JHAO on 2017/10/24.
 */

public interface BasicMapper<T> extends MySqlMapper<T>, Mapper<T> {

}

