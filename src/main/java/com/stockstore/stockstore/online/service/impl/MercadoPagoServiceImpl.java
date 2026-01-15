package com.stockstore.stockstore.online.service.impl;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.stockstore.stockstore.exception.BadRequestException;
import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.online.dto.preference.PreferenceRequestDTO;
import com.stockstore.stockstore.online.model.CartItem;
import com.stockstore.stockstore.online.repository.CartRepository;
import com.stockstore.stockstore.online.service.MercadoPagoService;
import com.stockstore.stockstore.shared.model.Product;
import com.stockstore.stockstore.shared.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MercadoPagoServiceImpl implements MercadoPagoService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Override
    public Preference createPreference(List<PreferenceRequestDTO> requests){
        List<PreferenceItemRequest> items = new ArrayList<>();

        for(PreferenceRequestDTO dto : requests){
            Product product = productRepository.findByIdAndEnabledTrue(dto.productId())
                    .orElseThrow(() -> new NotFoundException("Product ID does not exist: " + dto.productId()));

            items.add(productToPreferenceItemRequest(product, dto.quantity()));
        }

        return savePreference(items);
    }
    
    @Override
    public Preference createPreference(String email){
        List<CartItem> cartItems = (cartRepository.findByUserEmail(email)
                .orElseThrow(()-> new NotFoundException("Invalid Cart"))).getItems();
        if(cartItems.isEmpty()){
            throw new BadRequestException("Cart is empty");
        }
        List<PreferenceItemRequest> items = new ArrayList<>();

        for(CartItem cartItem:cartItems){
            items.add(productToPreferenceItemRequest(cartItem.getProduct(), cartItem.getQuantity()));
        }
        return savePreference(items);
    }

    private PreferenceItemRequest productToPreferenceItemRequest(Product product, int quantity){
        return PreferenceItemRequest.builder()
                .title(product.getName())
                .currencyId("ARS")
                .pictureUrl(product.getImageUrl())
                .unitPrice(product.getPrice())
                .quantity(quantity)
                .build();
    }

    private Preference savePreference(List<PreferenceItemRequest> items){
        try {
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(PreferenceBackUrlsRequest.builder()
                            .success("https://www.google.com.ar")
                            .failure("https://www.bing.com")
                            .build())
                    .autoReturn("approved")
                    .build();

            PreferenceClient client = new PreferenceClient();
            return client.create(preferenceRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
