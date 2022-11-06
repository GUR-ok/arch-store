package ru.gur.archstore.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gur.archstore.entity.Product;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "FROM Product AS p WHERE p.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")}) //ms
    Optional<Product> findByIdLocked(@Param("id") UUID id);
}
