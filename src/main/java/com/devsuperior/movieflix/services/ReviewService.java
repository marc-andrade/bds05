package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<ReviewDTO> findAllPaged(Pageable pageable) {
        Page<Review> list = repository.findAll(pageable);
        return list.map(ReviewDTO::new);
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Review entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object not found."));
        return new ReviewDTO(entity);
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        return new ReviewDTO(repository.save(entity));
    }

    @Transactional
    public ReviewDTO update(Long id, ReviewDTO dto) {
        try {
            Review genre = repository.getOne(id);
            copyDtoToEntity(dto, genre);
            return new ReviewDTO(repository.save(genre));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ReviewDTO dto, Review entity) {

        entity.setText(dto.getText());
        entity.setUser(userRepository.getOne(dto.getUserDTO().getId()));
        entity.setMovie(movieRepository.getOne(dto.getMovieDTO().getId()));
    }

}
