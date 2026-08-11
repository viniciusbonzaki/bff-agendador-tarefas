package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.business.UsuarioService;
import com.javanauta.bffagendadortarefas.business.dtos.EnderecoDTO;
import com.javanauta.bffagendadortarefas.business.dtos.TelefoneDTO;
import com.javanauta.bffagendadortarefas.business.dtos.UsuarioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas ao Usuário")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuário", description = "Cria um novo Usuário e salva seus Dados")
    @ApiResponse(responseCode = "200", description = "Usuário adicionado com Sucesso!")
    @ApiResponse(responseCode = "400", description = "Usuário já Cadastrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de Usuário", description = "Realiza o Login de um Usuário através de Email e Senha")
    @ApiResponse(responseCode = "200", description = "Login realizado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public String loginUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados do Usuário", description = "Busca os dados de um Usuário através do Email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com Sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar dados do Usuário", description = "Deleta os dados de um Usuário através do Email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com Sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do Usuário", description = "Atualiza os dados de um Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com Sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<UsuarioDTO> atualizarDadosUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar um Endereço de Usuário", description = "Atualiza os dados de Endereço de um Usuário através ID do Enderéco e do Token")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com Sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<EnderecoDTO> atualizarDadosEndereco(@RequestParam("id") Long id,
                                                              @RequestBody EnderecoDTO enderecoDTO,
                                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar um Telefone de Usuário", description = "Atualiza os dados de Telefone de um Usuário através do ID do Telefone e do Token")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com Sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TelefoneDTO> atualizarDadosTelefone(@RequestParam("id") Long id,
                                                              @RequestBody TelefoneDTO telefoneDTO,
                                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar um Endereço de Usuário", description = "Adiciona um Endereço para um Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Endereço adicionado com Sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestHeader("Authorization") String token,
                                                         @RequestBody EnderecoDTO enderecoDTO) {
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar um Telefone de Usuário", description = "Adiciona um Telefone para um Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Telefone adicionado com Sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestHeader("Authorization") String token,
                                                         @RequestBody TelefoneDTO telefoneDTO) {
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}
