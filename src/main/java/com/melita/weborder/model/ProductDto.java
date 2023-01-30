package com.melita.weborder.model;

import com.melita.weborder.model.enums.PackageType;
import com.melita.weborder.model.enums.ProductType;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private ProductType productType;
    private PackageType packageType;

}
