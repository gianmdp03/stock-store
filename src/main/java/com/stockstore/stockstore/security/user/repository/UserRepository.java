package com.stockstore.stockstore.security.user.repository;

import com.stockstore.stockstore.security.user.Enum.Role;
import com.stockstore.stockstore.security.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Page<User> findByIsBannedFalse(Pageable pageable);
    Page<User> findByIsBannedTrue(Pageable pageable);
    Page<User> findByRoleAndIsBannedFalse(Role role, Pageable pageable);
    List<User> findByRole(Role role);
}
