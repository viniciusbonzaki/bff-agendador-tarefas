package com.javanauta.bffagendadortarefas.business.dtos.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTORequest {
    private String estado;
    private String cidade;
    private String rua;
    private Long numero;
    private String cep;
    private String complemento;
}
