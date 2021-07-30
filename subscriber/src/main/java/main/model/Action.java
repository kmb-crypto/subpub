package main.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class Action extends BaseEntity {
    private Timestamp timestamp;

    @Column(name = "message_id")
    private long messageId;
}
