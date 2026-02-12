package com.stockstore.stockstore.security.user.service;

import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserDetailDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdateDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdatePassDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthenticationService {
    AuthenticationResponseDTO register(UserRequestDTO request);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    void forgotPassword(String email);
    String validateCode(String email, String code);
    boolean changeForgottenPassword(String email, String token, String password);
    boolean changePassword(String email, UserUpdatePassDTO dto);
    void updateUser(String email, UserUpdateDTO dto);
    Page<UserDetailDTO> listUsers(Pageable pageable);
    Page<UserDetailDTO> listBannedUsers(Pageable pageable);
    Page<UserDetailDTO> listEmployees(Pageable pageable);
    Page<UserDetailDTO> searchUsersByEmail(String email, Pageable pageable);
    UserDetailDTO getUserByEmail(String email);
    void promoteToEmployee(Long id);
    void promoteToAdmin(Long id);
    void toggleBan(Long id);
    UserDetailDTO getUserById(Long id);
    void updateUserAsAdmin(Long id, UserUpdateDTO dto);
}
