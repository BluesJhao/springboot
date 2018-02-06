package com.example.springboot.web.aop;

import com.example.springboot.exception.ProcessException;
import com.example.springboot.utils.MailUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * service层exception处理
 *
 * @author panhc
 * @create 2017-07-03-15:17
 */
@Aspect
@Configuration
public class ServiceExceptionHandler {

    private static final String MAIL_SUBJECT = "Service接口异常";
    private static final String MAIL_MODEL_ADDRESS = "exception_mail";

    @SuppressWarnings("unused")
    @Autowired
    private MailUtil mailUtil;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.springboot.service.*.*(..))")
    public void servicePointCut() {
    }

    @AfterThrowing(pointcut = "servicePointCut()", throwing = "e")
    public void handle(JoinPoint point, Throwable e) {
        //业务异常不要处理
        if (e instanceof ProcessException) {
            log.error("ProcessException do not need handle:{}", e);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        String method = point.getSignature().getName();
        String clazzName = point.getSignature().getDeclaringType().getName();
        String exception = ExceptionUtils.getStackTrace(e);
        map.put("method", method);
        map.put("clazzName", clazzName);
        map.put("exception", exception);
        mailUtil.send(MAIL_MODEL_ADDRESS, map, new String[]{"lijiahao@gomeholdings.com"}, MAIL_SUBJECT);
    }
}
