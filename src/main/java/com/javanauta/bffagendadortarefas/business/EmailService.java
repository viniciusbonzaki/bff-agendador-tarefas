package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.EmailClient;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefaDTOResponse tarefaDTO) {
        emailClient.enviarEmail(tarefaDTO);
    }
}
