package com.khun.movievault.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String movieTitle;

    @Column(length = 2000)
    private String overview;
    private LocalDate releaseDate;
    private String poster;
    private  Double rating;
    private String trailer;

    @ManyToMany(mappedBy = "favouriteMovies")
    private List<Profile> profiles;
}
