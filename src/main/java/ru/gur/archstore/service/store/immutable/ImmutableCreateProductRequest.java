package ru.gur.archstore.service.store.immutable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ImmutableCreateProductRequest {

    String name;

    String description;

    Double price;

    Boolean isAvailable;

    Long quantity;
}
