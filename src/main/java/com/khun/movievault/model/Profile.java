package com.khun.movievault.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String fullName;
    private String contactNo;
    @Nullable
    private String imageUrl;

    @OneToOne(mappedBy = "profile")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "favourite_movies",
            joinColumns = {@JoinColumn(name = "profile_id", referencedColumnName = "profileId")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "movieId")}
    )
    private List<Movie> favouriteMovies;

}
