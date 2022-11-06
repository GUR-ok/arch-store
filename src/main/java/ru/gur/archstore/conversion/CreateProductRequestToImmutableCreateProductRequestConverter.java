package ru.gur.archstore.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.gur.archstore.service.store.immutable.ImmutableCreateProductRequest;
import ru.gur.archstore.web.store.request.CreateProductRequest;

@Component
public class CreateProductRequestToImmutableCreateProductRequestConverter
        implements Converter<CreateProductRequest, ImmutableCreateProductRequest> {

    @Override
    public ImmutableCreateProductRequest convert(final CreateProductRequest source) {
        return ImmutableCreateProductRequest.builder()
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .isAvailable(source.getIsAvailable())
                .quantity(source.getQuantity())
                .build();
    }
}