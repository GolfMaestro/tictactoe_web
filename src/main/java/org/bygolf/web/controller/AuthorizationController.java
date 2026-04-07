package org.bygolf.web.controller;

import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.domain.model.SignUpRequest;
import org.bygolf.domain.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
public class AuthorizationController {

    private final UserServiceImpl userServiceImpl;

    public AuthorizationController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registration(@RequestBody SignUpRequest signUpRequest) {

        boolean res = userServiceImpl.registration(signUpRequest);

        if (res) {
            return ResponseEntity.ok().body("Successful sign up. Now you can login");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login already in use. Try again");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authHeader) {

        String input = authHeader.substring("Basic ".length());

        byte[] decoded = Base64.getDecoder().decode(input);
        String credentials = new String(decoded);

        String[] values = credentials.split(":", 2);

        String login = values[0];
        String password = values[1];

        UUID id = userServiceImpl.login(login, password);

        if (id != null) {
            return ResponseEntity.ok().body(id);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong login or password or user don't exists");
        }

    }

}
