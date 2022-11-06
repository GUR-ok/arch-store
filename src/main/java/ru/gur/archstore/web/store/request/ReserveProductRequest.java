package ru.gur.archstore.web.store.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class ReserveProductRequest {

    @NotNull
    private UUID processId;

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID productId;

    @NotNull
    private Long quantity;
}
