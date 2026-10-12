package com.javanauta.bffagendadortarefas.infrastructure.exceptions;

public class ErrorResponseReadException extends RuntimeException {
    public ErrorResponseReadException(String mensagem) {
        super(mensagem);
    }

    public ErrorResponseReadException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
