package com.stockstore.stockstore.shared.dto.order;

import com.stockstore.stockstore.shared.dto.product.ProductListDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDetailDTO(Long id, BigDecimal amount, String description, LocalDate saleDate, List<ProductListDTO> products) {}
