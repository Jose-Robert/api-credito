package br.com.money.credito.exception;

public class CampoValorNegativoException extends RuntimeException {

    public CampoValorNegativoException(String message) {
        super(message);
    }
}
