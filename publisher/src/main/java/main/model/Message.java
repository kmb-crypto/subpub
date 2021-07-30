package main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class Message {
    private long id;

    @JsonProperty("msisdn")
    private long subscriberId;

    private String action;
    private Timestamp timestamp;

    public Message(long id, long subscriberId, String action) {
        this.id = id;
        this.subscriberId = subscriberId;
        this.action = action;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
