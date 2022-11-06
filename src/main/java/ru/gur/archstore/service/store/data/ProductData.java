package ru.gur.archstore.service.store.data;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ProductData {

    UUID id;

    String name;

    String description;

    Double price;

    Boolean isAvailable;

    Long quantity;
}
