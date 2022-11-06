package ru.gur.archstore.service.kafka;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductReserveCancelEventData.class, name = "PRODUCT_RESERVE_CANCEL")
})
public interface KafkaEvent extends EventSource{
}