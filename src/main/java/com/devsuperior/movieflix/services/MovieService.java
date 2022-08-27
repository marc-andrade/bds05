package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
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
public class MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAllPaged(Pageable pageable){
        Page<Movie> list = repository.findAll(pageable);
        return list.map(MovieDTO::new);
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Object not found."));
        return new MovieDTO(entity);
    }

    @Transactional
    public MovieDTO insert(MovieDTO dto) {
        Movie entity = new Movie();
        copyDtoToEntity(dto, entity);
        return new MovieDTO(repository.save(entity));
    }

    @Transactional
    public MovieDTO update(Long id, MovieDTO dto) {
        try {
            Movie entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            return new MovieDTO(repository.save(entity));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found" + id);

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(MovieDTO dto, Movie entity) {

        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setYear(dto.getYear());
        entity.setImgUrl(dto.getImgUrl());
        entity.setSynopsis(dto.getSynopsis());
        entity.setGenre(genreRepository.getOne(dto.getId()));

    }

}
