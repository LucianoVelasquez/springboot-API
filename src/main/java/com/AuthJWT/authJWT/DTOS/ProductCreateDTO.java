package com.AuthJWT.authJWT.DTOS;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateDTO {
    @NotEmpty
    @Size(min = 3,max = 20)
    private String name;
    @NotNull
    private Integer stock;
    @NotNull
    private Double price;
    @NotEmpty
    @Size(min = 3,max = 20)
    private String type;

    public @NotEmpty @Size(min = 3, max = 20) String getName() {
        return name;
    }

    public void setName(@NotEmpty @Size(min = 3, max = 20) String name) {
        this.name = name;
    }

    public @NotNull Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull Integer stock) {
        this.stock = stock;
    }

    public @NotNull Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull Double price) {
        this.price = price;
    }

    public @NotEmpty @Size(min = 3, max = 20) String getType() {
        return type;
    }

    public void setType(@NotEmpty @Size(min = 3, max = 20) String type) {
        this.type = type;
    }
}
