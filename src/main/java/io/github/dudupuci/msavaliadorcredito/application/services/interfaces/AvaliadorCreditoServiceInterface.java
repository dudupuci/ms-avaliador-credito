package io.github.dudupuci.msavaliadorcredito.application.services.interfaces;

import io.github.dudupuci.msavaliadorcredito.application.dtos.DadosAvaliacaoRequest;
import io.github.dudupuci.msavaliadorcredito.application.presentation.CartaoAprovadoDtoResponse;
import io.github.dudupuci.msavaliadorcredito.application.presentation.RetornoAvaliacaoCliente;
import io.github.dudupuci.msavaliadorcredito.domain.exception.DadosClienteNotFoundException;
import io.github.dudupuci.msavaliadorcredito.domain.exception.ErroComunicacaoMicroservicesException;
import io.github.dudupuci.msavaliadorcredito.domain.model.SituacaoCliente;

import java.util.List;

public interface AvaliadorCreditoServiceInterface {
    SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException;
    RetornoAvaliacaoCliente realizarAvaliacao(DadosAvaliacaoRequest request) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException;
}
