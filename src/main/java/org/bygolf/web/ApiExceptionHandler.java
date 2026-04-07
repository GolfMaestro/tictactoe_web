package org.bygolf.web;

import org.bygolf.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<String> handleGameNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Game not found");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You don't have access to this game");
    }

    @ExceptionHandler(IncorrectMoveException.class)
    public ResponseEntity<String> handleIncorrectMove() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Incorrect move");
    }

    @ExceptionHandler(GameIsFullException.class)
    public ResponseEntity<String> handleGameIsFull() {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body("Game already full");
    }

    @ExceptionHandler(PlayWithYourselfException.class)
    public ResponseEntity<String> handlePlayWithYourself() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("You can't play game with yourself");
    }

}
