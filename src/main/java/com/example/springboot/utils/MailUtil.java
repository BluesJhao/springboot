package com.example.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 发送邮件工具类
 *
 * @author panhc
 * @create 2017-05-31-17:44
 */
@Component("mailUtil")
public class MailUtil {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.from}")
    private String sendFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    /**
     * 发送邮件方法
     * @param templateName 模板名称
     * @param model 参数
     * @param sendTo 收件人
     * @param subject 发送主题
     * @return
     */
    public boolean send(String templateName, Map<String,Object> model,String[] sendTo,String subject) {
        boolean success = true;
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
            String result = null;
            try {
                Context context = new Context();
                context.setVariables(model);
                context.setVariable("sendTime", new Timestamp(System.currentTimeMillis()).toString());
                result = templateEngine.process(templateName,context);
            } catch (Exception e) {e.printStackTrace();}
            messageHelper.setTo(sendTo);
            messageHelper.setFrom(sendFrom);
            messageHelper.setSubject(subject);
            messageHelper.setText(result,true);
            javaMailSender.send(mailMessage);
            log.info("templateName:{};sendTo:{};",templateName,sendTo);
        } catch (Exception e) {
            success = false;
            log.error("templateName:{};sendTo:{};Exception:{}",templateName,sendTo,e);
        }
        return success;
    }

}
