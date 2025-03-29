package com.learningSpringSecurity.LearnSpringSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class JwtResponseDTO {

    private String Status;
    private String JwtToken;

    public String getStatus() {
        return Status;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public JwtResponseDTO(String status, String jwtToken) {
        Status = status;
        JwtToken = jwtToken;
    }

    public JwtResponseDTO() {
    }
}
