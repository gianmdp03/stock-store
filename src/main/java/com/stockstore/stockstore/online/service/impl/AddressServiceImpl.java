package com.stockstore.stockstore.online.service.impl;

import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.online.dto.address.AddressDetailDTO;
import com.stockstore.stockstore.online.dto.address.AddressRequestDTO;
import com.stockstore.stockstore.online.dto.address.AddressUpdateDTO;
import com.stockstore.stockstore.online.mapper.AddressMapper;
import com.stockstore.stockstore.online.model.Address;
import com.stockstore.stockstore.online.repository.AddressRepository;
import com.stockstore.stockstore.online.service.AddressService;
import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AddressDetailDTO addAddress(String email, AddressRequestDTO dto) {
        Optional<Address> existingAddress = addressRepository.findByNameAndUserEmail(dto.name(), email);
        if(existingAddress.isPresent()){
            Address address = existingAddress.get();
            address.setEnabled(true);
            address.setApartment(dto.apartment());
            address.setCity(dto.city());
            address.setNumber(dto.number());
            address.setStreet(dto.street());
            address.setProvince(dto.province());
            address.setZipCode(dto.zipCode());
            return addressMapper.toDetailDto(address);
        }
        Address address = addressMapper.toEntity(dto);
        User user = userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("Invalid user"));
        address.setUser(user);
        address = addressRepository.save(address);
        return addressMapper.toDetailDto(address);
    }

    @Override
    public Page<AddressDetailDTO> listAddresses(String email, Pageable pageable) {
        Page<Address> page = addressRepository.findByUserEmailAndEnabledTrue(email, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(addressMapper::toDetailDto);
    }

    @Override
    public AddressDetailDTO getAddressById(String email, Long id) {
        Address address = addressRepository.findByIdAndUserEmailAndEnabledTrue(id, email)
                .orElseThrow(()-> new NotFoundException("Address ID does not exist"));
        return addressMapper.toDetailDto(address);
    }

    @Override
    @Transactional
    public AddressDetailDTO updateAddress(String email, Long id, AddressUpdateDTO dto) {
        Address address = addressRepository.findByIdAndUserEmailAndEnabledTrue(id, email)
                .orElseThrow(()-> new NotFoundException("Address ID does not exist"));
        addressMapper.updateEntityFromDto(dto, address);
        address = addressRepository.save(address);
        return addressMapper.toDetailDto(address);
    }

    @Override
    @Transactional
    public void deleteAddress(String email, Long id) {
        Address address = addressRepository.findByIdAndUserEmailAndEnabledTrue(id, email)
                .orElseThrow(()-> new NotFoundException("Address ID does not exist"));
        address.setEnabled(false);
    }
}
