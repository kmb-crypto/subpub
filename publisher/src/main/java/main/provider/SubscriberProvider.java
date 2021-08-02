package main.provider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.Subscriber;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
public class SubscriberProvider {
    private static final long MAX_SUBSCRIBER_ID = 999_999_999;

    public Subscriber getSubscriber() {
        return new Subscriber(MAX_SUBSCRIBER_ID);
    }
}
