package ru.gur.archstore.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gur.archstore.entity.OrderedProduct;
import ru.gur.archstore.entity.OrderedProductId;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductId> {

    @Query("FROM OrderedProduct AS op WHERE op.orderedProductId.orderId = :id")
    List<OrderedProduct> findAllByOrderId(@Param("id") UUID orderId);
}
