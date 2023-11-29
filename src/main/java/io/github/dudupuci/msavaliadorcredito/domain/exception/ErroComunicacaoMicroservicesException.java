package io.github.dudupuci.msavaliadorcredito.domain.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception {

    @Getter
    private Integer status;
    public ErroComunicacaoMicroservicesException(String message, Integer status) {
        super(message);
    }
}
