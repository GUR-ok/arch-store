package ru.gur.archstore.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.gur.archstore.service.store.immutable.ImmutableReserveProductRequest;
import ru.gur.archstore.web.store.request.ReserveProductRequest;

@Component
public class ReserveProductRequestToImmutableReserveProductRequest
        implements Converter<ReserveProductRequest, ImmutableReserveProductRequest> {

    @Override
    public ImmutableReserveProductRequest convert(final ReserveProductRequest source) {
        return ImmutableReserveProductRequest.builder()
                .processId(source.getProcessId())
                .orderId(source.getOrderId())
                .productId(source.getProductId())
                .quantity(source.getQuantity())
                .build();
    }
}
