package com.pgkk.factory;

/**
 * Created by tanxueze on 2019/2/20.
 */

public class MailSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is mailsender");
    }
}
