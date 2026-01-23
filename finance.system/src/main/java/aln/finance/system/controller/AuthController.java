package aln.finance.system.controller;

import aln.finance.system.dto.LoginRequest;
import aln.finance.system.dto.LoginResponse;
import aln.finance.system.dto.RegisterUserResponseDto;
import aln.finance.system.model.User;
import aln.finance.system.repository.UserRepository;
import aln.finance.system.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
        public RegisterUserResponseDto createUser(@Valid @RequestBody User user){
        User createdUser = authService.createUser(user);
        return new RegisterUserResponseDto(createdUser.getId(), createdUser.getEmail(), createdUser.getCreatedAt());
        }
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest user){
        return authService.login(user);
    }

    }


