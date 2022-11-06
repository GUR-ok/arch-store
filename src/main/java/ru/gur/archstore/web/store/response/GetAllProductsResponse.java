package ru.gur.archstore.web.store.response;

import lombok.Builder;
import lombok.Data;
import ru.gur.archstore.service.store.data.ProductData;

import java.util.List;

@Data
@Builder
public class GetAllProductsResponse {

    List<ProductData> productDataList;
}
