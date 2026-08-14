package com.javanauta.bffagendadortarefas.business.dtos.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTORequest {
    private String email;
    private String senha;
}
