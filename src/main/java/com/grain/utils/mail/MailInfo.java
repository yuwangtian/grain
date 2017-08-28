package com.grain.utils.mail;

import java.io.Serializable;

/**
 * @author anqi
 * @since 2014/9/18.
 */
public class MailInfo implements Serializable {
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件接收者的地址
    private String toAddress;

    public String getToAddresss() {
        return toAddresss;
    }

    public void setToAddresss(String toAddresss) {
        this.toAddresss = toAddresss;
    }

    // 邮件接收者的地址
    private String toAddresss;

    private String contentType = "text/plain; charset=utf-8";

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
