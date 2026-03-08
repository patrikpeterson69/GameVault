package com.example.gamevault.dto;

import jakarta.validation.constraints.*;

public class GameDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Platform is required")
    private String platform;

    @Min(value = 1970, message = "Release year must be 1970 or later")
    @Max(value = 2100, message = "Release year seems invalid")
    private int releaseYear;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", message = "Rating must be at most 10")
    private Double rating;

    public GameDTO() {}

    public GameDTO(Long id, String title, String genre, String platform, int releaseYear, Double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
