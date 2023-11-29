package io.github.dudupuci.msavaliadorcredito.application.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovadoDtoResponse> cartoes;
}
