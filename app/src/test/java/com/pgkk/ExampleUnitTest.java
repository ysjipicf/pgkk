package com.pgkk;

import com.pgkk.factory.Provider;
import com.pgkk.factory.SendFactory;
import com.pgkk.factory.SendMailFactory;
import com.pgkk.factory.Sender;
import com.pgkk.singleton.Singleton;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);

        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.Send();

        SendFactory.produceMail().Send();

        Provider provider  = new SendMailFactory();
        Sender sender1 = provider.produce();
        sender1.Send();

        Singleton.getInstance().readResolve();
    }

}

