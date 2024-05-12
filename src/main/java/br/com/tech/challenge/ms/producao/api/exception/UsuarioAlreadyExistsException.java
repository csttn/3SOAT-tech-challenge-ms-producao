package br.com.tech.challenge.ms.producao.api.exception;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }

}
