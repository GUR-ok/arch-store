package ru.gur.archstore.service.kafka;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class SomeEventData implements KafkaEvent {

    UUID orderId;

    @Override
    public Event getEvent() {
        return Event.SOME;
    }
}
