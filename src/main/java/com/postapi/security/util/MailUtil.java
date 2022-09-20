package com.postapi.security.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailUtil {
    public static void main(String[] args) {
        testMessage("sarojdangol0214@gmail.com", "Hello", "Verify your email");
    }

    public static void testMessage(String email, String message, String subject) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Mail mail = new Mail();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setFrom("AusNepItSupport");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("AppName", "AusNepItSupport");
        mail.setModel(model);
        mailMessage.setText(message);
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("developer.ausnep@gmail.com");
        javaMailSender.setPassword("Ausnep##23");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        javaMailProperties.put("mail.smtp.ssl.enable", "true");
//        javaMailProperties.put("mail.smtp.timeout",3000);
        javaMailSender.setJavaMailProperties(javaMailProperties);
        javaMailSender.send(mailMessage);
    }
}
