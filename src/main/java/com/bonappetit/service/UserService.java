package com.bonappetit.service;

import com.bonappetit.model.dto.RegisterDTO;
import com.bonappetit.model.dto.UserDTO;
import com.bonappetit.model.entity.User;

import java.util.Optional;

public interface UserService {
    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email) ;

    boolean checkCredentials(String username, String password);

    void login(String username) ;

    void register(RegisterDTO registerDTO) ;

    void logout() ;

    void initAdmin();

    Optional<User> findUserById(Long id);

    void initTest();
}
