package com.pgkk.factory;

/**
 * Created by tanxueze on 2019/2/20.
 */

public class SendFactory {


    public static Sender produceSms() {
        return new SmsSender();
    }

    public static Sender produceMail() {
        return new MailSender();
    }

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("类型出错");
            return null;
        }
    }
}
