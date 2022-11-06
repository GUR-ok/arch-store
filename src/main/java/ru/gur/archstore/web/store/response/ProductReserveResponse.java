package ru.gur.archstore.web.store.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductReserveResponse {

    private UUID reserveId;

    private Double amount;
}
