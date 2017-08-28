package com.grain.utils.mail;

import com.grain.utils.PropertiesUtil;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @author anqi
 * @since 2014/9/18.
 */
public class MailSender {

    private static Properties properties = PropertiesUtil
            .loadProperties("/config.properties");

    private static String ONLINE = properties
            .getProperty("online");
    private static Properties mailConfig = PropertiesUtil.loadProperties("/mail.properties");
    private static Logger logger = Logger.getLogger(MailSender.class);

    /**
     * 异步方式发送邮件
     *
     * @param mailInfo
     */
    public static void send(final MailInfo mailInfo) {
        if (ONLINE != null && "0".equals(ONLINE)) {
            logger.info("非上线环境，不需要发邮件，/config.properties");
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    MailSender.sendEmail(mailInfo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 异步方式发送邮件
     *
     * @param mailInfo
     */
    public static void batchSend(final MailInfo mailInfo) {
        if (ONLINE != null && "0".equals(ONLINE)) {
            logger.info("非上线环境，不需要发邮件，/config.properties");
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    MailSender.batchSendEmail(mailInfo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static boolean sendEmail(MailInfo mailInfo) {
        // 判断是否需要身份认证
        Authenticator authenticator = null;
        //如果需要身份认证，则创建一个密码验证器
        if (Boolean.valueOf(mailConfig.getProperty("mail.smtp.auth"))) {
            String username = mailConfig.getProperty("mail.send.user");
            String password = mailConfig.getProperty("mail.send.password");
            authenticator = new UserAuthenticator(username, password);
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(mailConfig, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailConfig.getProperty("mail.send.from"));
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart body = new MimeBodyPart();
            // 设置HTML内容
            mailInfo.setContent(mailInfo.getContent() + "\n\n\n ---------------------------------------------------");
            mailInfo.setContent(mailInfo.getContent() + "\n\n webSite  :    " + mailConfig.getProperty("mail.send.website"));
            body.setContent(mailInfo.getContent(), mailInfo.getContentType());
            mainPart.addBodyPart(body);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException e) {
            logger.error("邮件发送失败", e);
        }
        return false;
    }

    //send mail to more than one recipient one time - add by qiaojingjun 20160713
    public static boolean batchSendEmail(MailInfo mailInfo) {
        // 判断是否需要身份认证
        Authenticator authenticator = null;
        //如果需要身份认证，则创建一个密码验证器
        if (Boolean.valueOf(mailConfig.getProperty("mail.smtp.auth"))) {
            String username = mailConfig.getProperty("mail.send.user");
            String password = mailConfig.getProperty("mail.send.password");
            authenticator = new UserAuthenticator(username, password);
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(mailConfig, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailConfig.getProperty("mail.send.from"));
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            String[] strAddresses = mailInfo.getToAddresss().split(";");
            Address[] addresses = new Address[strAddresses.length];
            for (int i = 0; i < strAddresses.length; i++) {
                addresses[i] = new InternetAddress(strAddresses[i]);
            }
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipients(Message.RecipientType.TO, addresses);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart body = new MimeBodyPart();
            // 设置HTML内容
            mailInfo.setContent(mailInfo.getContent() + "\n\n\n ---------------------------------------------------");
            mailInfo.setContent(mailInfo.getContent() + "\n\n webSite  :    " + mailConfig.getProperty("mail.send.website"));
            body.setContent(mailInfo.getContent(), mailInfo.getContentType());
            mainPart.addBodyPart(body);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException e) {
            logger.error("邮件发送失败", e);
        }
        return false;
    }

    private static final class UserAuthenticator extends Authenticator {
        private String userName;
        private String password;

        public UserAuthenticator(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }

}
