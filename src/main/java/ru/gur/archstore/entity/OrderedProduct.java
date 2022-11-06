package ru.gur.archstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordered_products")
public class OrderedProduct {

    @EmbeddedId
    private OrderedProductId orderedProductId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @MapsId("productId")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(columnDefinition = "uuid", name = "process_id", nullable = false)
    private UUID processId;
}
