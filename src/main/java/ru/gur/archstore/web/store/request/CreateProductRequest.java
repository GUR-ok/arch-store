package ru.gur.archstore.web.store.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateProductRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    private Long quantity;
}
