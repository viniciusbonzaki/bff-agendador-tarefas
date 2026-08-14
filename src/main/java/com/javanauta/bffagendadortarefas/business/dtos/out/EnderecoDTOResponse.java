package com.javanauta.bffagendadortarefas.business.dtos.out;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTOResponse {
    private Long id;
    private String estado;
    private String cidade;
    private String rua;
    private Long numero;
    private String cep;
    private String complemento;
}
