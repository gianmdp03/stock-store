package com.stockstore.stockstore.online.repository;

import com.stockstore.stockstore.online.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByNameAndUserEmail(String name, String email);
    Page<Address> findByUserEmailAndEnabledTrue(String email, Pageable pageable);
    Optional<Address> findByIdAndUserEmailAndEnabledTrue(Long id, String email);
}
