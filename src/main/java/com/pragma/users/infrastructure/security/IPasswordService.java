package com.pragma.users.infrastructure.security;

public interface IPasswordService {
    String encryptPassword(String password);
    boolean verifyPassword(String rawPassword, String encryptedPassword);
}
