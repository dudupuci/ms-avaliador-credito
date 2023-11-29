package io.github.dudupuci.msavaliadorcredito.application.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosAvaliacaoRequest {
    private String cpf;
    private Long renda;
}
