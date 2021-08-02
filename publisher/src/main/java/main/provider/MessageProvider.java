package main.provider;

import main.model.ActionType;
import main.model.Message;
import main.gear.MessageIdHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProvider {
    private static final double ACTION_CHOOSE_FACTOR = 0.5;
    private final SubscriberProvider subscriberProvider;
    private final MessageIdHolder messageIdHolder;

    @Autowired
    public MessageProvider(final SubscriberProvider subscriberProvider, final MessageIdHolder messageIdHolder) {
        this.subscriberProvider = subscriberProvider;
        this.messageIdHolder = messageIdHolder;
        System.out.println("Message Provider created");
    }

    public Message getMessage() {
        return new Message(messageIdHolder.getId(), subscriberProvider.getSubscriber().getId(), getActionType());
    }

    private String getActionType() {
        return (Math.random() > ACTION_CHOOSE_FACTOR) ? ActionType.PURCHASE.name() : ActionType.SUBSCRIPTION.name();
    }
}
