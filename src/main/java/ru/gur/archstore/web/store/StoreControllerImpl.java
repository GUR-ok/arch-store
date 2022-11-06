package ru.gur.archstore.web.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gur.archstore.service.store.StoreService;
import ru.gur.archstore.service.store.immutable.ImmutableCreateProductRequest;
import ru.gur.archstore.service.store.immutable.ImmutableReserveProductRequest;
import ru.gur.archstore.web.store.request.CreateProductRequest;
import ru.gur.archstore.web.store.request.ReserveProductRequest;
import ru.gur.archstore.web.store.response.GetOrderReserveResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreControllerImpl implements StoreController {

    private final StoreService storeService;
    private final ConversionService conversionService;

    @Override
    @PostMapping("/products")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UUID reserveProduct(@Valid @NotNull @RequestBody final ReserveProductRequest reserveProductRequest) {
        log.debug("ReserveProductRequest: " + reserveProductRequest);

        return storeService.reserveProduct(conversionService.convert(reserveProductRequest, ImmutableReserveProductRequest.class));
    }

    @Override
    @GetMapping(path = "/products/{id}")
    public GetOrderReserveResponse getReservedProductByOrderId(@PathVariable(name = "id") final UUID orderId) {
        return GetOrderReserveResponse.builder()
                .orderedProductDataList(storeService.getReservedProductByOrderId(orderId))
                .build();
    }

    @Override
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UUID addProductToStore(@Valid @NotNull @RequestBody final CreateProductRequest createProductRequest) {
        log.debug("CreateProductRequest: " + createProductRequest);

        return storeService.addProductToStore(conversionService.convert(createProductRequest, ImmutableCreateProductRequest.class));
    }
}
