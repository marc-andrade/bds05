package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO {

    private Long id;
    private String text;
    private UserDTO userDTO;
    private MovieDTO movieDTO;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text, UserDTO userDTO, MovieDTO movieDTO) {
        this.id = id;
        this.text = text;
        this.userDTO = userDTO;
        this.movieDTO = movieDTO;
    }

    public ReviewDTO(Review review) {
        id = review.getId();
        text = review.getText();
        userDTO = new UserDTO(review.getUser());
        movieDTO = new MovieDTO(review.getMovie());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public MovieDTO getMovieDTO() {
        return movieDTO;
    }

    public void setMovieDTO(MovieDTO movieDTO) {
        this.movieDTO = movieDTO;
    }
}
