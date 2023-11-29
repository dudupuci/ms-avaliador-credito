package io.github.dudupuci.msavaliadorcredito.domain.exception;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados");
    }
}
