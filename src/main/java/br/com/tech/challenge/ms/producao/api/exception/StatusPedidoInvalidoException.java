package br.com.tech.challenge.ms.producao.api.exception;

public class StatusPedidoInvalidoException extends RuntimeException {
    public StatusPedidoInvalidoException() {
        super("Status inválido");
    }
}
