package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dtos.in.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    // Email e Senha para um Usuário que irá fazer o Cron funcionar
    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    // Método responsável por implementar um Sistema Cron
    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora() {
        // Pegar o Token do Usuário responsável pelo Cron
        String token = usuarioService.loginUsuario(converterParaLoginDTORequest());

        // Definir os Tempo de Atuação do Cron (Procurar as Tarefas para daqui 1 Hora de 5 em 5 Minutos)
        LocalDateTime proximaHora = LocalDateTime.now().plusHours(1);
        LocalDateTime proximaHoraMaisCinto = LocalDateTime.now().plusHours(1).plusMinutes(5);

        // Realizar a Busca pelas Tarefas
        List<TarefaDTOResponse> listaTarefas = tarefaService.buscarTarefasPorPeriodo(proximaHora, proximaHoraMaisCinto, token);

        // Enviar Notificação para cada uma das Tarefas e alterar o Status para Notificado
        listaTarefas.forEach(
                tarefa -> { emailService.enviarEmail(tarefa);
                                              tarefaService.alterarStatusTarefa(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
                });

    }

    // Método para gerar um Token para o Usuário responsável pelo Cron
    private String login(LoginDTORequest loginDTO) {
        return usuarioService.loginUsuario(loginDTO);
    }

    // Método para poder converter para um DTO para utilizar o Método de Login
    private LoginDTORequest converterParaLoginDTORequest() {
        return LoginDTORequest.builder()
                .email(this.email)
                .senha(this.senha)
                .build();
    }
}
