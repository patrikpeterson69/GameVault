package com.example.gamevault.service;

import com.example.gamevault.dto.GameDTO;
import com.example.gamevault.exception.GameNotFoundException;
import com.example.gamevault.model.Game;
import com.example.gamevault.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// @ExtendWith tells JUnit to use Mockito to set up mocks automatically
@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    // @Mock creates a fake version of GameRepository.
    // It doesn't touch a real database - we control what it returns.
    @Mock
    private GameRepository gameRepository;

    // @InjectMocks creates a real GameService and injects the mock repository into it.
    // This way we test only the service logic, nothing else.
    @InjectMocks
    private GameService gameService;

    private Game sampleGame;

    // @BeforeEach runs before every single test method
    @BeforeEach
    void setUp() {
        sampleGame = new Game("Elden Ring", "RPG", "PC", 2022, 9.5);
        sampleGame.setId(1L);
    }

    @Test
    void getAllGames_returnsListOfDTOs() {
        // Arrange: tell the mock what to return when findAll() is called
        when(gameRepository.findAll()).thenReturn(List.of(sampleGame));

        // Act: call the real service method
        List<GameDTO> result = gameService.getAllGames();

        // Assert: verify the result is what we expect
        assertEquals(1, result.size());
        assertEquals("Elden Ring", result.get(0).getTitle());
    }

    @Test
    void getGameById_returnsCorrectGame() {
        // Arrange: simulate finding a game by id
        when(gameRepository.findById(1L)).thenReturn(Optional.of(sampleGame));

        // Act
        GameDTO result = gameService.getGameById(1L);

        // Assert
        assertEquals("Elden Ring", result.getTitle());
        assertEquals("RPG", result.getGenre());
    }

    @Test
    void getGameById_throwsExceptionWhenNotFound() {
        // Arrange: simulate that no game exists with id 99
        when(gameRepository.findById(99L)).thenReturn(Optional.empty());

        // Assert: calling getGameById(99) should throw GameNotFoundException
        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(99L));
    }

    @Test
    void createGame_savesAndReturnsDTO() {
        // Arrange
        GameDTO inputDTO = new GameDTO(null, "Hades", "Roguelite", "PC", 2020, 9.3);
        Game savedGame = new Game("Hades", "Roguelite", "PC", 2020, 9.3);
        savedGame.setId(2L);

        // Simulate that save() returns the saved game with an id assigned
        when(gameRepository.save(any(Game.class))).thenReturn(savedGame);

        // Act
        GameDTO result = gameService.createGame(inputDTO);

        // Assert
        assertEquals(2L, result.getId());
        assertEquals("Hades", result.getTitle());
        // Verify that save() was actually called once
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    void deleteGame_throwsExceptionWhenNotFound() {
        // Arrange: simulate that game with id 99 does not exist
        when(gameRepository.existsById(99L)).thenReturn(false);

        // Assert
        assertThrows(GameNotFoundException.class, () -> gameService.deleteGame(99L));
    }
}
