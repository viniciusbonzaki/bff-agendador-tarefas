package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    // Método para mostrar uma Mensagem mais amigável dependendo da Exceção
    @Override
    public Exception decode(String methodKey, Response response) {
       switch (response.status()) {
           case 401: {
               return new UnauthorizedException("Erro: Usuário não Autorizado!");
           }
           case 403: {
               return new ResourceNotFoundException("Erro: Atributo não Encontrado!");
           }
           case 409: {
               return new ConflictException("Erro: Atributo já Existente!");
           }
           default: {
                return new BusinessException("Erro de Servidor!");
           }
       }
    }
}
