package org.bygolf.domain.service;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.model.DSUser;
import org.bygolf.datasource.repository.UserRepository;
import org.bygolf.domain.model.SignUpRequest;
import org.bygolf.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DSToDomain dsToDomain;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, DSToDomain dsToDomain, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dsToDomain = dsToDomain;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByLogin(String login) {

        DSUser dsUser = userRepository.findByLogin(login);

        User user = dsToDomain.dsToUser(dsUser);

        return user;
    }

    @Override
    public boolean registration(SignUpRequest signUpRequest) {

        DSUser dsUser = userRepository.findByLogin(signUpRequest.getLogin());

        if (dsUser == null) {
            String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
            userRepository.save(new DSUser(signUpRequest.getLogin(), hashedPassword));
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public UUID login(String login, String password) {

        DSUser dsUser = userRepository.findByLogin(login);

        if (dsUser != null) {
            User user = dsToDomain.dsToUser(dsUser);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user.getId();
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

    }
}
