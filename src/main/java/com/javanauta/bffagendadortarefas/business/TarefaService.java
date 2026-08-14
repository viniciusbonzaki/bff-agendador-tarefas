package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.TarefaClient;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaClient tarefaClient;

    // Método para Salvar uma Tarefa que será atribuída à um Usuário
    public TarefaDTOResponse gravarTarefa(String token, TarefaDTORequest tarefaDTO) {
        return tarefaClient.salvarTarefa(token, tarefaDTO);
    }

    // Método que irá buscar as Tarefas de todos os Usuário entre um Período de Tempo
    public List<TarefaDTOResponse> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefaClient.buscarListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    // Método para buscar todas as Tarefas de um determinado Usuário
    public List<TarefaDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefaClient.buscarListaTarefasPorEmail(token);
    }

    // Método para deletar uma Tarefa de um determinado Usuário
    public void deletarTarefaPorId(String id, String token) {
        tarefaClient.deletarTarefaPorId(id, token);
    }

    // Método para alterar o Status da Tarefa de um determinado Usuário
    public TarefaDTOResponse alterarStatusTarefa(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.atualizarStatusNotificacao(status, id, token);
    }

    // Método para alterar os dados da Tarefa de um determinado Usuário
    public TarefaDTOResponse alterarTarefa(TarefaDTORequest tarefaDTO, String id, String token) {
        return tarefaClient.atualizarTarefa(tarefaDTO, id, token);
    }
}
