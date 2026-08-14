package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dtos.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dtos.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dtos.out.UsuarioDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    // Método para Salvar os Dados do Usuário no Banco
    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    // Método para realizar o Login do Usuário
    public String loginUsuario(LoginDTORequest loginDTO) {
        return usuarioClient.loginUsuario(loginDTO);
    }

    // Buscar um Usuário pelo Email utilizando o Método "findByEmail" de UsuarioRepository
    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    // Método para deletar um Usuário pelo Email
    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    // Método para atualizar os dadods de um Usuário pelo Email
    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return usuarioClient.atualizarDadosUsuario(usuarioDTO, token);
    }

    // Método para atualizar os dados de um Endereço pelo ID
    public EnderecoDTOResponse atualizarEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizarDadosEndereco(idEndereco, enderecoDTO, token);
    }

    // Método para atualizar os dados de um Telefone pelo ID
    public TelefoneDTOResponse atualizarTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizarDadosTelefone(idTelefone, telefoneDTO, token);
    }

    // Método para adicionar um novo Endereço à um Usuário pelo seu Email
    public EnderecoDTOResponse cadastrarEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return usuarioClient.cadastrarEndereco(token, enderecoDTO);
    }

    // Método para adicionar um novo Telefone à um Usuário pelo seu Email
    public TelefoneDTOResponse cadastrarTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return usuarioClient.cadastrarTelefone(token, telefoneDTO);
    }
}
