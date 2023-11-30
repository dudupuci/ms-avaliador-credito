package io.github.dudupuci.msavaliadorcredito.application.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoCartao {
    private Long id;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
