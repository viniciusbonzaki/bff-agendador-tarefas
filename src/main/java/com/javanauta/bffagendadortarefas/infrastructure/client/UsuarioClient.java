package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dtos.EnderecoDTO;
import com.javanauta.bffagendadortarefas.business.dtos.TelefoneDTO;
import com.javanauta.bffagendadortarefas.business.dtos.UsuarioDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
    String loginUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @GetMapping
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTO atualizarDadosUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTO atualizarDadosEndereco(@RequestParam("id") Long id,
                                      @RequestBody EnderecoDTO enderecoDTO,
                                      @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizarDadosTelefone(@RequestParam("id") Long id,
                                       @RequestBody TelefoneDTO telefoneDTO,
                                       @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastrarEndereco(@RequestHeader("Authorization") String token,
                                  @RequestBody EnderecoDTO enderecoDTO);

    @PostMapping("/telefone")
    TelefoneDTO cadastrarTelefone(@RequestHeader("Authorization") String token,
                                  @RequestBody TelefoneDTO telefoneDTO);
}
