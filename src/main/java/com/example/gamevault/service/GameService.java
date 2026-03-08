package com.example.gamevault.service;

import com.example.gamevault.dto.GameDTO;
import com.example.gamevault.exception.GameNotFoundException;
import com.example.gamevault.model.Game;
import com.example.gamevault.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public GameDTO getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        return toDTO(game);
    }

    public GameDTO createGame(GameDTO dto) {
        Game game = toEntity(dto);
        return toDTO(gameRepository.save(game));
    }

    public GameDTO updateGame(Long id, GameDTO dto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setPlatform(dto.getPlatform());
        game.setReleaseYear(dto.getReleaseYear());
        game.setRating(dto.getRating());

        return toDTO(gameRepository.save(game));
    }

    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new GameNotFoundException(id);
        }
        gameRepository.deleteById(id);
    }

    public List<GameDTO> searchByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<GameDTO> filterByGenre(String genre) {
        return gameRepository.findByGenreIgnoreCase(genre)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<GameDTO> filterByPlatform(String platform) {
        return gameRepository.findByPlatformIgnoreCase(platform)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private GameDTO toDTO(Game game) {
        return new GameDTO(
                game.getId(),
                game.getTitle(),
                game.getGenre(),
                game.getPlatform(),
                game.getReleaseYear(),
                game.getRating()
        );
    }

    private Game toEntity(GameDTO dto) {
        return new Game(
                dto.getTitle(),
                dto.getGenre(),
                dto.getPlatform(),
                dto.getReleaseYear(),
                dto.getRating()
        );
    }
}
