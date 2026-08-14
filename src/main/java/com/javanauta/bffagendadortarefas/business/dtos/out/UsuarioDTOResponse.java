package com.javanauta.bffagendadortarefas.business.dtos.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTOResponse {
    private String nome;
    private String senha;
    private String email;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse> telefones;
}
