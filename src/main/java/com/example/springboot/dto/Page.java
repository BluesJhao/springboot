package com.example.springboot.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页封装
 * @author panhc
 * @create 2017年4月10日
 */
@Data
public class Page<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private int total;

    private List<T> rows;

    private int pageSize;

    private int offset;


    public Page() {
    }

    public Page(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 封装分页
     * @param row 每页条数
     * @param page 当前页
     */
    public Page(int row, int page) {
        this.offset = (page - 1) * row;
        this.pageSize = row;
    }

    public Page<T> hold(List<T> rows,int total){
        this.rows = rows;
        this.total = total;
        return this;
    }
}
