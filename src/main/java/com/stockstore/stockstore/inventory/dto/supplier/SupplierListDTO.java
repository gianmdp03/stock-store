package com.stockstore.stockstore.inventory.dto.supplier;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.util.List;

public record SupplierListDTO (Long id, String name, String email, String phoneNumber, List<ProductListDTO> products){
}
