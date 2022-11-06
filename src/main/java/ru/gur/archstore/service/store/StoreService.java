package ru.gur.archstore.service.store;

import ru.gur.archstore.service.store.data.OrderedProductData;
import ru.gur.archstore.service.store.immutable.ImmutableCreateProductRequest;
import ru.gur.archstore.service.store.immutable.ImmutableReserveProductRequest;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    UUID reserveProduct(ImmutableReserveProductRequest immutableReserveProductRequest);

    List<OrderedProductData> getReservedProductByOrderId(UUID orderId);

    void cancelReservationByOrderId(UUID orderId);

    UUID addProductToStore(ImmutableCreateProductRequest immutableCreateProductRequest);
}
