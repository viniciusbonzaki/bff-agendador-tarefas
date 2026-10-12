package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.*;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;

import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    // Método para mostrar uma Mensagem mais amigável dependendo da Exceção
    @Override
    public Exception decode(String methodKey, Response response) {

        // Pegar a Mensagem de Erro
        String mensagemErro = pegarMensagemErro(response);
        String erro = "Erro: ";

       switch (response.status()) {
           case 400: {
               return new IllegalArgumentException(erro + mensagemErro);
           }
           case 401: {
               return new UnauthorizedException(erro + mensagemErro);
           }
           case 403: {
               return new ResourceNotFoundException(erro + mensagemErro);
           }
           case 409: {
               return new ConflictException(erro + mensagemErro);
           }
           default: {
                return new BusinessException(erro + mensagemErro);
           }
       }
    }

    // Método para pegar a Mensagem de Erro
    private String pegarMensagemErro(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            throw new ErrorResponseReadException("Não foi possível ler o corpo da resposta HTTP!", e.getCause());
        }
    }
}
