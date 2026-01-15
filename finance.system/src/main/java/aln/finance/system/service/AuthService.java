package aln.finance.system.service;

import aln.finance.system.model.User;
import aln.finance.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        if(userRepository.existsByEmail
                (user.getEmail()))
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        return userRepository.save(user);
    }
}
