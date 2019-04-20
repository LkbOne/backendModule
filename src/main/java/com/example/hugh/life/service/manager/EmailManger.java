package com.example.hugh.life.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailManger {
    @Autowired
    private JavaMailSender javaMailSender;
    private String from = "276699567@qq.com";

    public boolean sendSimpleMail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        }catch (RuntimeException e){
            return false;
        }
        return true;
    }

    public boolean sendHtmlMail(String to, String subject, String text){

        try {
            MimeMessage message = null;
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);

            StringBuffer sb = new StringBuffer();
                    sb.append("LIFE登陆验证码：").append("<p style='color:#F00'>").append(text).append("</p>");
            helper.setText(sb.toString(), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
