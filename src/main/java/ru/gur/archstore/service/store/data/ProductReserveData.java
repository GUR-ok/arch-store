package ru.gur.archstore.service.store.data;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ProductReserveData {

    UUID reserveId;

    Double amount;
}
