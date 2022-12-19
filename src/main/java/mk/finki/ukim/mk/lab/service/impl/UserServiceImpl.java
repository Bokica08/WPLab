package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.Exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaUserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final JpaUserRepository userRepository;

    public UserServiceImpl(JpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws WrongCredentialsException {
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(UserNotFound::new);
    }
}
