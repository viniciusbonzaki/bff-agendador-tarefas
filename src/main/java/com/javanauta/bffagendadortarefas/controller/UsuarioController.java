package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.business.UsuarioService;
import com.javanauta.bffagendadortarefas.business.dtos.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.UsuarioDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.ViaCepDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas aos Usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)  // Anotação para o Token JWT funcionar no Swagger OpeanAPI
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuário", description = "Cria um novo Usuário e salva seus Dados")
    @ApiResponse(responseCode = "200", description = "Usuário adicionado com Sucesso!")
    @ApiResponse(responseCode = "409", description = "Usuário já Cadastrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de Usuário", description = "Realiza o Login de um Usuário através de Email e Senha")
    @ApiResponse(responseCode = "200", description = "Login realizado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public String loginUsuario(@RequestBody LoginDTORequest loginDTO) {
        return usuarioService.loginUsuario(loginDTO);
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Coletar dados do Enderéco", description = "Coleta os dados de um Endereço através do CEP")
    @ApiResponse(responseCode = "200", description = "Dados do Endereço coletados com Sucesso!")
    @ApiResponse(responseCode = "400", description = "Formato do CEP é Inválido!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<ViaCepDTOResponse> buscarDadosCEP(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(usuarioService.buscarDadosEndereco(cep));
    }

    @GetMapping
    @Operation(summary = "Buscar dados do Usuário", description = "Busca os dados de um Usuário através do Email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar dados do Usuário", description = "Deleta os dados de um Usuário através do Email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do Usuário", description = "Atualiza os dados de um Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar um Endereço de Usuário", description = "Atualiza os dados de Endereço de um Usuário através ID do Enderéco e do Token")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<EnderecoDTOResponse> atualizarDadosEndereco(@RequestParam("id") Long id,
                                                                      @RequestBody EnderecoDTORequest enderecoDTO,
                                                                      @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar um Telefone de Usuário", description = "Atualiza os dados de Telefone de um Usuário através do ID do Telefone e do Token")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TelefoneDTOResponse> atualizarDadosTelefone(@RequestParam("id") Long id,
                                                                      @RequestBody TelefoneDTORequest telefoneDTO,
                                                                      @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar um Endereço de Usuário", description = "Adiciona um Endereço para um Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Endereço adicionado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco(@RequestHeader(value = "Authorization", required = false) String token,
                                                                 @RequestBody EnderecoDTORequest enderecoDTO) {
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar um Telefone de Usuário", description = "Adiciona um Telefone para um Usuário através do Token")
    @ApiResponse(responseCode = "200", description = "Telefone adicionado com Sucesso!")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas!")
    @ApiResponse(responseCode = "403", description = "Usuário não Encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor!")
    public ResponseEntity<TelefoneDTOResponse> cadastrarTelefone(@RequestHeader(value = "Authorization", required = false) String token,
                                                                 @RequestBody TelefoneDTORequest telefoneDTO) {
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}
