package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dtos.EnderecoDTO;
import com.javanauta.bffagendadortarefas.business.dtos.TelefoneDTO;
import com.javanauta.bffagendadortarefas.business.dtos.UsuarioDTO;
import com.javanauta.bffagendadortarefas.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    // Método para Salvar os Dados do Usuário no Banco
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    // Método para realizar o Login do Usuário
    public String loginUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.loginUsuario(usuarioDTO);
    }

    // Buscar um Usuário pelo Email utilizando o Método "findByEmail" de UsuarioRepository
    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    // Método para deletar um Usuário pelo Email
    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    // Método para atualizar os dadods de um Usuário pelo Email
    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO usuarioDTO) {
        return usuarioClient.atualizarDadosUsuario(usuarioDTO, token);
    }

    // Método para atualizar os dados de um Endereço pelo ID
    public EnderecoDTO atualizarEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token) {
        return usuarioClient.atualizarDadosEndereco(idEndereco, enderecoDTO, token);
    }

    // Método para atualizar os dados de um Telefone pelo ID
    public TelefoneDTO atualizarTelefone(Long idTelefone, TelefoneDTO telefoneDTO, String token) {
        return usuarioClient.atualizarDadosTelefone(idTelefone, telefoneDTO, token);
    }

    // Método para adicionar um novo Endereço à um Usuário pelo seu Email
    public EnderecoDTO cadastrarEndereco(String token, EnderecoDTO enderecoDTO) {
        return usuarioClient.cadastrarEndereco(token, enderecoDTO);
    }

    // Método para adicionar um novo Telefone à um Usuário pelo seu Email
    public TelefoneDTO cadastrarTelefone(String token, TelefoneDTO telefoneDTO) {
        return usuarioClient.cadastrarTelefone(token, telefoneDTO);
    }
}
