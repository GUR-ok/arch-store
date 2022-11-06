package ru.gur.archstore.web.store;

import ru.gur.archstore.web.store.request.CreateProductRequest;
import ru.gur.archstore.web.store.request.ReserveProductRequest;
import ru.gur.archstore.web.store.response.GetAllProductsResponse;
import ru.gur.archstore.web.store.response.GetOrderReserveResponse;

import java.util.UUID;

public interface StoreController {

    UUID reserveProduct(ReserveProductRequest reserveProductRequest);

    GetOrderReserveResponse getReservedProductByOrderId(UUID orderId);

    UUID addProductToStore(CreateProductRequest createProductRequest);

    GetAllProductsResponse getAllProducts();
}
