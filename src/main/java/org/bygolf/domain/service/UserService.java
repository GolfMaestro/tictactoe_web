package org.bygolf.domain.service;

import org.bygolf.domain.model.SignUpRequest;
import org.bygolf.domain.model.User;

import java.util.UUID;

public interface UserService {

    User getUserByLogin(String login);
    boolean registration(SignUpRequest signUpRequest);
    UUID login(String login, String password);

}
