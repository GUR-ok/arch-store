package ru.gur.archstore.service.store.immutable;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ImmutableReserveProductRequest {

    UUID processId;

    UUID orderId;

    UUID productId;

    Long quantity;
}
