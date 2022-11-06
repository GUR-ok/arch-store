package ru.gur.archstore.service.store.data;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class OrderedProductData {

    UUID productId;

    UUID orderId;

    String name;

    String description;

    Double price;
}
