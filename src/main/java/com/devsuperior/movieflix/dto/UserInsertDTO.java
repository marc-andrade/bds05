package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO{
    private String password;

    public UserInsertDTO() {
    }

    public UserInsertDTO(Long id, String name, String email, String password) {
        super(id, name, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
