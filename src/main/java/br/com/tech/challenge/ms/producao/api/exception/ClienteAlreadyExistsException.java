package br.com.tech.challenge.ms.producao.api.exception;

public class ClienteAlreadyExistsException extends RuntimeException {

    public ClienteAlreadyExistsException(String message) {
        super(message);
    }

}
