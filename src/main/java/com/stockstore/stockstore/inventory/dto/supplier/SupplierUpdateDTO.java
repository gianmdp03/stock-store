package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.Email;

import java.util.List;

public record SupplierUpdateDTO (String name, @Email String email, String phoneNumber, List<Long> productIds){
}
