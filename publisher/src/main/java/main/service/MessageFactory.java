package main.service;

import main.model.ActionType;
import main.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageFactory {
    private static final double ACTION_CHOOSE_FACTOR = 0.5;
    private final SubscriberFactory subscriberFactory;
    private final MessageIdHolder messageIdHolder;

    @Autowired
    public MessageFactory(final SubscriberFactory subscriberFactory, final MessageIdHolder messageIdHolder) {
        this.subscriberFactory = subscriberFactory;
        this.messageIdHolder = messageIdHolder;
    }

    public Message getMessage() {
        return new Message(messageIdHolder.getId(), subscriberFactory.getSubscriber().getId(), getActionType());
    }

    private String getActionType() {
        return (Math.random() > ACTION_CHOOSE_FACTOR) ? ActionType.PURCHASE.name() : ActionType.SUBSCRIPTION.name();
    }
}
