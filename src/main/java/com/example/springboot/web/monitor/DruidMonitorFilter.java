package com.example.springboot.web.monitor;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Desc ：DruidMonitor Filter
 * Created by JHAO on 2017/10/31.
 */
@WebFilter(filterName="druidMonitorFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
public class DruidMonitorFilter extends WebStatFilter {
}
