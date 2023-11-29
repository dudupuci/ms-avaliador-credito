package io.github.dudupuci.msavaliadorcredito.application.presentation;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class CartaoDtoResponse {
    private String nome;
    private String bandeira;
    private BigDecimal renda;
    private BigDecimal limite;
}
