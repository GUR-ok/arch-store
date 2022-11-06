package ru.gur.archstore.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.gur.archstore.service.store.data.ProductReserveData;
import ru.gur.archstore.web.store.response.ProductReserveResponse;

@Component
public class ProductReserveDataToProductReserveResponse
        implements Converter<ProductReserveData, ProductReserveResponse> {

    @Override
    public ProductReserveResponse convert(final ProductReserveData source) {
        return ProductReserveResponse.builder()
                .reserveId(source.getReserveId())
                .amount(source.getAmount())
                .build();
    }
}
