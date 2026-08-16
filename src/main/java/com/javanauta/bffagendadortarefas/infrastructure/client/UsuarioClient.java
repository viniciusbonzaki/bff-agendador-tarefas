package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dtos.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.UsuarioDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.ViaCepDTOResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {
    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String loginUsuario(@RequestBody LoginDTORequest loginDTO);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCEP(@PathVariable("cep") String cep);

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarDadosEndereco(@RequestParam("id") Long id,
                                               @RequestBody EnderecoDTORequest enderecoDTO,
                                               @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarDadosTelefone(@RequestParam("id") Long id,
                                               @RequestBody TelefoneDTORequest telefoneDTO,
                                               @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco(@RequestHeader("Authorization") String token,
                                          @RequestBody EnderecoDTORequest enderecoDTO);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastrarTelefone(@RequestHeader("Authorization") String token,
                                          @RequestBody TelefoneDTORequest telefoneDTO);
}
