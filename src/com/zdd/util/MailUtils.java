package com.zdd.util;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
    /**
     * @param to       收件人地址
     * @param emailMsg 消息内容
     * @throws MessagingException
     * @throws GeneralSecurityException
     */
    public static void sendMail(String to, String emailMsg)
            throws MessagingException, GeneralSecurityException {

//        // 收件人电子邮箱
//        String to = "86431843@qq.com";

        // 发件人电子邮箱
        String from = "1075987401@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 指定验证为true
        properties.put("mail.smtp.auth", "true");


        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1075987401@qq.com", "cuebijjxuoukichi"); //发件人邮件用户名、密码
            }
        });

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);

        //设置发送者
        message.setFrom(new InternetAddress(from));

        //设置发送方式与接收者
        message.setRecipient(RecipientType.TO, new InternetAddress(to));

        //设置邮件主题
        message.setSubject("用户激活");

        //设置邮件内容
        message.setContent(emailMsg, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送
        Transport.send(message);
    }
}
