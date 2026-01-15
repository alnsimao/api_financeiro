package aln.finance.system.service;

import aln.finance.system.dto.LoginRequest;
import aln.finance.system.model.User;
import aln.finance.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private  UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public User createUser(User user) {
        if(userRepository.existsByEmail
                (user.getEmail()))
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginRequest login(LoginRequest loginRequest) {
        Optional<User> userLogin = userRepository.findByEmail(loginRequest.getEmail());

        if(userLogin.isEmpty())
            throw new RuntimeException("User not found");

        User user = userLogin.get();

        String hashedPassword = user.getPassword();
        boolean matches = encoder.matches(loginRequest.getPassword(), hashedPassword);

    }
}
