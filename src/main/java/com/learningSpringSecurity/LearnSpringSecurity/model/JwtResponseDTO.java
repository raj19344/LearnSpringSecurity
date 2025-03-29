package com.learningSpringSecurity.LearnSpringSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class JwtResponseDTO {

    private String JwtToken;
    private String Status;
}
