package com.example.gamevault.controller;

import com.example.gamevault.dto.GameDTO;
import com.example.gamevault.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController tells Spring this class handles HTTP requests and returns JSON
// @RequestMapping sets the base URL for all endpoints in this class: /api/games
@RestController
@RequestMapping("/api/games")
public class GameController {

    // We inject GameService through the constructor (called dependency injection).
    // Spring creates the service object and passes it in automatically.
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // GET /api/games → returns all games
    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    // GET /api/games/5 → returns one game by id
    // @PathVariable reads the "5" from the URL and maps it to the id parameter
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    // GET /api/games/search?title=zelda → searches by title
    // @RequestParam reads query parameters from the URL (the part after ?)
    @GetMapping("/search")
    public ResponseEntity<List<GameDTO>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(gameService.searchByTitle(title));
    }

    // GET /api/games/filter?genre=RPG or /api/games/filter?platform=PC
    // Both parameters are optional (required = false), so we check which one was sent
    @GetMapping("/filter")
    public ResponseEntity<List<GameDTO>> filterGames(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String platform) {

        if (genre != null) {
            return ResponseEntity.ok(gameService.filterByGenre(genre));
        }
        if (platform != null) {
            return ResponseEntity.ok(gameService.filterByPlatform(platform));
        }
        // No filter provided → return all games
        return ResponseEntity.ok(gameService.getAllGames());
    }

    // POST /api/games → creates a new game
    // @RequestBody reads the JSON from the request body and maps it to a GameDTO object
    // @Valid triggers the validation rules we defined in GameDTO (e.g. @NotBlank)
    @PostMapping
    public ResponseEntity<GameDTO> createGame(@Valid @RequestBody GameDTO dto) {
        GameDTO created = gameService.createGame(dto);
        // 201 Created is more correct than 200 OK when something new is created
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/games/5 → updates an existing game completely
    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @Valid @RequestBody GameDTO dto) {
        return ResponseEntity.ok(gameService.updateGame(id, dto));
    }

    // DELETE /api/games/5 → deletes a game
    // Returns 204 No Content, which is standard for successful deletes (nothing to return)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
