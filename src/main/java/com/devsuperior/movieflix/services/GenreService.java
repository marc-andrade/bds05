package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
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
public class GenreService {

    @Autowired
    private GenreRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<GenreDTO> findAllPaged(Pageable pageable){
        Page<Genre> list = repository.findAll(pageable);
        return list.map(GenreDTO::new);
    }

    @Transactional(readOnly = true)
    public GenreDTO findById(Long id) {
        Genre entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Object not found."));
        return new GenreDTO(entity);
    }

    @Transactional
    public GenreDTO insert(GenreDTO dto) {
        Genre entity = new Genre();
        copyDtoToEntity(dto, entity);
        return new GenreDTO(repository.save(entity));
    }

    @Transactional
    public GenreDTO update(Long id, GenreDTO dto) {
        try {
            Genre genre = repository.getOne(id);
            copyDtoToEntity(dto, genre);
            return new GenreDTO(repository.save(genre));
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

    private void copyDtoToEntity(GenreDTO dto, Genre entity) {

        entity.setName(dto.getName());

    }

}
