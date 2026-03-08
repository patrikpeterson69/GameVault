package com.example.gamevault.exception;

// RuntimeException means we don't have to declare it with "throws" everywhere.
// It will bubble up automatically if not caught.
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long id) {
        // Calls the parent constructor with a clear error message
        super("Game not found with id: " + id);
    }
}
