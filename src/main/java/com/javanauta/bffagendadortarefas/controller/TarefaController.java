package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.business.TarefaService;
import com.javanauta.bffagendadortarefas.business.dtos.in.TarefaDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.TarefaDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Operações relacionadas as Tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)  // Anotação para o Token JWT funcionar no Swagger OpeanAPI
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuário", description = "Cria uma nova Tarefa e atribuí ela à um Usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa adicionada com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuário não Autorizado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TarefaDTOResponse> salvarTarefa(@RequestHeader(value = "Authorization", required = false) String token,
                                                          @RequestBody TarefaDTORequest tarefaDTO) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar Tarefas por Período", description = "Busca todas as Tarefas de Usuários entre um Perído de Tempo")
    @ApiResponse(responseCode = "200", description = "Tarefas dentro do Período encontradas com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuário não Autorizado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar Tarefas de Usuário", description = "Busca todas as Tarefas de um determinado Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuário não Autorizado!")
    @ApiResponse(responseCode = "403", description = "Email de Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefasPorEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        List<TarefaDTOResponse> listaTarefas = tarefaService.buscarTarefasPorEmail(token);
        return ResponseEntity.ok(listaTarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deletar Tarefa de Usuário", description = "Deleta uma Tarefa de um determinado Usuário através do ID da Tarefa e do Token")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuário não Autorizado!")
    @ApiResponse(responseCode = "403", description = "ID da Tarefa não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader(value = "Authorization", required = false) String token) {
        tarefaService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar o Status da Tarefa", description = "Altera o Status de uma determinada Tarefa através do ID da Tarefa e do Token")
    @ApiResponse(responseCode = "200", description = "Status da Tarefa alterado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuário não Autorizado!")
    @ApiResponse(responseCode = "403", description = "ID da Tarefa não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TarefaDTOResponse> atualizarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                        @RequestParam("id") String id,
                                                                        @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alterarStatusTarefa(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Alterar uma Tarefa de Usuário", description = "Altera os dados da Tarefa de um determinado Usuário através do ID da Tarefa e do Token")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Usuário não Autorizado!")
    @ApiResponse(responseCode = "403", description = "ID da Tarefa não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TarefaDTOResponse> atualizarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alterarTarefa(tarefaDTO, id, token));
    }
}
