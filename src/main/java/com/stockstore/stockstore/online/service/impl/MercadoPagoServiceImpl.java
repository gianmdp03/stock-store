package com.stockstore.stockstore.online.service.impl;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.online.dto.preference.PreferenceRequestDTO;
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

    public Preference createPreference(List<PreferenceRequestDTO> requests){
        try{
        List<PreferenceItemRequest> preferenceItemRequest = new ArrayList<>();
        for(PreferenceRequestDTO dto:requests){
            Product product = productRepository.findByIdAndEnabledTrue(dto.productId())
                    .orElseThrow(()-> new NotFoundException("Product ID does not exist"));
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title(product.getName())
                    .currencyId("ARS")
                    .pictureUrl(product.getImageUrl())
                    .unitPrice(product.getPrice())
                    .quantity(dto.quantity())
                    .build();

            preferenceItemRequest.add(item);
        }

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(preferenceItemRequest)
                .backUrls(PreferenceBackUrlsRequest.builder()
                        .success("https://www.google.com.ar")
                        .failure("https://www.bing.com")
                        .build())
                .autoReturn("approved")
                .build();

            PreferenceClient client = new PreferenceClient();
            return client.create(preferenceRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
