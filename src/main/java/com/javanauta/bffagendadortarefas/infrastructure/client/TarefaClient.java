package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefaClient {
    @PostMapping
    TarefaDTOResponse salvarTarefa(@RequestHeader("Authorization") String token,
                                   @RequestBody TarefaDTORequest tarefaDTO);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token
    );

    @GetMapping
    List<TarefaDTOResponse> buscarListaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTOResponse atualizarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                 @RequestParam("id") String id,
                                                 @RequestHeader("Authorization") String token);
    @PutMapping
    TarefaDTOResponse atualizarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String token);
}
