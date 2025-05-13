package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.AuthRequest;
import alatoo.web.taskmanagementapp.dto.AuthResponse;
import alatoo.web.taskmanagementapp.dto.RegisterRequest;
import alatoo.web.taskmanagementapp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Initiate login and send 2FA code to email")
    @ApiResponse(responseCode = "200", description = "OTP sent to email", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping("/login")
    public String login(@RequestBody @Valid AuthRequest request) throws MessagingException {
        return authService.initiateLogin(request);
    }

    @Operation(summary = "Verify 2FA code and receive access & refresh tokens")
    @ApiResponse(responseCode = "200", description = "Tokens generated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)))
    @PostMapping("/verify-otp")
    public AuthResponse verifyOtp(
            @Parameter(description = "Username of the user") @RequestParam String username,
            @Parameter(description = "One-time password received via email") @RequestParam String otp) {
        return authService.verifyOtp(username, otp);
    }

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(summary = "Refresh access token using a valid refresh token")
    @ApiResponse(responseCode = "200", description = "New access token generated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)))
    @PostMapping("/refresh")
    public AuthResponse refresh(@Parameter(description = "Refresh token issued during login") @RequestParam String token) {
        return authService.refreshToken(token);
    }
}
