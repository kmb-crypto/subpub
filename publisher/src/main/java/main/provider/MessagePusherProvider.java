package main.provider;

import main.PubConfig;
import main.gear.MessagePusher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MessagePusherProvider {

    public MessagePusher getMessageGenerator() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PubConfig.class);
        return context.getBean("messagePusher", MessagePusher.class);
    }
}
