package io.github.dudupuci.msavaliadorcredito.application.presentation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovadoDtoResponse {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
