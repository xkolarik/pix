package br.com.bancogestao.exception;

public class ContaJaExistenteException extends RuntimeException {
    public ContaJaExistenteException(String message) {
        super(message);
    }
}