package com.example.springsessionproject.web.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    private Integer price;

}
