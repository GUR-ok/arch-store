package ru.gur.archstore.service.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.gur.archstore.entity.OrderedProduct;
import ru.gur.archstore.entity.OrderedProductId;
import ru.gur.archstore.entity.Product;
import ru.gur.archstore.persistance.OrderedProductRepository;
import ru.gur.archstore.persistance.ProductRepository;
import ru.gur.archstore.service.store.data.OrderedProductData;
import ru.gur.archstore.service.store.immutable.ImmutableCreateProductRequest;
import ru.gur.archstore.service.store.immutable.ImmutableReserveProductRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final ProductRepository productRepository;
    private final OrderedProductRepository orderedProductRepository;

    @Override
    @Transactional
    public UUID reserveProduct(final ImmutableReserveProductRequest immutableReserveProductRequest) {
        Assert.notNull(immutableReserveProductRequest, "immutableReserveProductRequest must not be null");

        final UUID productId = immutableReserveProductRequest.getProductId();
        final Product product = productRepository.findByIdLocked(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));

        final Long orderedQuantity = immutableReserveProductRequest.getQuantity();
        if (product.getQuantity() < orderedQuantity || !product.getIsAvailable()) {
            throw new RuntimeException("not enough quantity");
        }

        product.setQuantity(product.getQuantity() - immutableReserveProductRequest.getQuantity());

        productRepository.save(product);

        final OrderedProductId orderedProductId = new OrderedProductId();
        orderedProductId.setProductId(product.getId());
        orderedProductId.setOrderId(immutableReserveProductRequest.getOrderId());

        final OrderedProduct orderedProduct = orderedProductRepository.findById(orderedProductId)
                .orElse(new OrderedProduct());

        orderedProduct.setProduct(product);
        orderedProduct.setQuantity(orderedQuantity);
        orderedProduct.setOrderedProductId(orderedProductId);
        orderedProduct.setProcessId(immutableReserveProductRequest.getProcessId());

        orderedProductRepository.save(orderedProduct);

        return immutableReserveProductRequest.getOrderId();
    }

    @Override
    public List<OrderedProductData> getReservedProductByOrderId(final UUID orderId) {
        final List<OrderedProduct> orderedProductList = orderedProductRepository.findAllByOrderId(orderId);

        return orderedProductList.stream()
                .map(op -> OrderedProductData.builder()
                        .name(op.getProduct().getName())
                        .description(op.getProduct().getDescription())
                        .orderId(op.getOrderedProductId().getOrderId())
                        .productId(op.getOrderedProductId().getProductId())
                        .price(op.getQuantity() * op.getProduct().getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelReservationByOrderId(final UUID orderId) {
        Assert.notNull(orderId, "orderId must not be null");

        final List<OrderedProduct> orderedProductList = orderedProductRepository.findAllByOrderId(orderId);

        final Map<UUID, Long> productIdsAndQuantities = orderedProductList.stream()
                .collect(Collectors.toMap(op -> op.getOrderedProductId().getProductId(), OrderedProduct::getQuantity));

        final List<Product> products = productRepository.findAllById(productIdsAndQuantities.keySet());
        products.forEach(p -> p.setQuantity(p.getQuantity() + productIdsAndQuantities.get(p.getId())));

        orderedProductRepository.deleteAllById(orderedProductList.stream()
                .map(OrderedProduct::getOrderedProductId)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public UUID addProductToStore(final ImmutableCreateProductRequest immutableCreateProductRequest) {
        Assert.notNull(immutableCreateProductRequest, "immutableCreateProductRequest must not be null");

        log.debug("ImmutableCreateProductRequest: " + immutableCreateProductRequest);

        final Product product = new Product();
        product.setName(immutableCreateProductRequest.getName());
        product.setDescription(immutableCreateProductRequest.getDescription());
        product.setQuantity(immutableCreateProductRequest.getQuantity());
        product.setIsAvailable(immutableCreateProductRequest.getIsAvailable());
        product.setPrice(immutableCreateProductRequest.getPrice());

        log.debug("Product: " + product);

        return productRepository.save(product).getId();
    }
}
