package com.stockstore.stockstore.inventory.service;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.inventory.model.Supplier;
import com.stockstore.stockstore.inventory.repository.SupplierRepository;
import com.stockstore.stockstore.shared.dto.Batch.BatchRequestDTO;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import com.stockstore.stockstore.shared.service.EmaiLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierOrderService {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final EmaiLService emailService;

    public void processSupplierOrder(Long supplierId, BatchRequestDTO orderDetails) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"));

        StringBuilder body = new StringBuilder();
        body.append("Estimado/a ").append(supplier.getName()).append(",\n\n");
        body.append("Deseamos realizar el siguiente pedido de productos:\n\n");
        
        //El cuerpo del email
        orderDetails.items().forEach(item -> {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new NotFoundException("Producto ID " + item.productId() + " Does Not exist"));
            body.append("- ").append(product.getName())
                    .append(" | Cantidad: ").append(item.quantity()).append("\n");
        });

        body.append("\nQuedamos a la espera de su confirmación.\nSaludos,\nAdministración StockStore.");

        // 3. Enviar el email usando la API de Gmail
        emailService.sendSimpleEmail(
                supplier.getEmail(),
                "Nuevo Pedido de Productos - StockStore",
                body.toString()
        );
    }
}