package main.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscriber {
    private long id;

    public Subscriber(long max) {
        this.id = (long) (Math.random() * max);
    }
}
