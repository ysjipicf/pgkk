package com.pgkk.factory;

/**
 * Created by tanxueze on 2019/2/20.
 */

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
