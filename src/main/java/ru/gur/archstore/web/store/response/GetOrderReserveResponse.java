package ru.gur.archstore.web.store.response;

import lombok.Builder;
import lombok.Data;
import ru.gur.archstore.service.store.data.OrderedProductData;

import java.util.List;

@Data
@Builder
public class GetOrderReserveResponse {

    private List<OrderedProductData> orderedProductDataList;
}
