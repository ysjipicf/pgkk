package com.pgkk.factory;

/**
 * Created by tanxueze on 2019/2/20.
 */

public class SendSmsFactory implements Provider{

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
