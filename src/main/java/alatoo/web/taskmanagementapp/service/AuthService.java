package alatoo.web.taskmanagementapp.service;

import alatoo.web.taskmanagementapp.dto.AuthRequest;
import alatoo.web.taskmanagementapp.dto.AuthResponse;
import alatoo.web.taskmanagementapp.dto.RegisterRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    public String initiateLogin(AuthRequest request) throws MessagingException;
    public AuthResponse verifyOtp(String username, String otp);
    public String register(RegisterRequest request);
    public AuthResponse refreshToken(String token);

}
