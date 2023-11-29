package io.github.dudupuci.msavaliadorcredito.domain.model;

import lombok.Data;

@Data // mesmos dados da entidade cliente, que vou receber na API
public class DadosCliente {
    private Long id;
    private String nome;
    private Integer idade;
}
