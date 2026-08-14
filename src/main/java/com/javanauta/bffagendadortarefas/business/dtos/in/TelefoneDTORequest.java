package com.javanauta.bffagendadortarefas.business.dtos.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTORequest {
    private String numero;
    private String ddd;
}
