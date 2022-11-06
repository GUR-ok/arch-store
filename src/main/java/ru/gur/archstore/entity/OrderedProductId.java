package ru.gur.archstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class OrderedProductId implements Serializable {

    @Column(columnDefinition = "uuid", name="order_id", nullable=false)
    private UUID orderId;
    private UUID productId;
}
