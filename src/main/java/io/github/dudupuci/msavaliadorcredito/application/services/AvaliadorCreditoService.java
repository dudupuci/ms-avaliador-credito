package io.github.dudupuci.msavaliadorcredito.application.services;

import feign.FeignException;
import io.github.dudupuci.msavaliadorcredito.application.dtos.DadosAvaliacaoRequest;
import io.github.dudupuci.msavaliadorcredito.application.feign.CartoesFeignClient;
import io.github.dudupuci.msavaliadorcredito.application.feign.ClientesFeignClient;
import io.github.dudupuci.msavaliadorcredito.application.presentation.CartaoAprovadoDtoResponse;
import io.github.dudupuci.msavaliadorcredito.application.presentation.CartaoDtoResponse;
import io.github.dudupuci.msavaliadorcredito.application.presentation.RetornoAvaliacaoCliente;
import io.github.dudupuci.msavaliadorcredito.application.services.interfaces.AvaliadorCreditoServiceInterface;
import io.github.dudupuci.msavaliadorcredito.domain.exception.DadosClienteNotFoundException;
import io.github.dudupuci.msavaliadorcredito.domain.exception.ErroComunicacaoMicroservicesException;
import io.github.dudupuci.msavaliadorcredito.domain.model.DadosCliente;
import io.github.dudupuci.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService implements AvaliadorCreditoServiceInterface {

    private final ClientesFeignClient clientesFeignClient;
    private final CartoesFeignClient cartoesFeignClient;

    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            var dadosCliente = this.clientesFeignClient.getDadosClienteByCpf(cpf);
            var dadosCartao = this.cartoesFeignClient.getCartoesByCpf(cpf);

            return SituacaoCliente.builder().cliente(dadosCliente.getBody()).cartoes(dadosCartao.getBody()).build();

        } catch (FeignException.FeignClientException err) {
            int status = err.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(err.getMessage(), status);
        }
    }

    @Override
    public RetornoAvaliacaoCliente realizarAvaliacao(DadosAvaliacaoRequest request) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteByCpf = this.clientesFeignClient.getDadosClienteByCpf(request.getCpf());
            ResponseEntity<List<CartaoDtoResponse>> cartoesRendaAte = this.cartoesFeignClient.getCartoesRendaAte(request.getRenda());

            List<CartaoDtoResponse> cartoesFiltrados = cartoesRendaAte.getBody();
            List<CartaoAprovadoDtoResponse> cartoesAprovados = cartoesFiltrados.stream().map(cartao -> {

                BigDecimal limite = cartao.getLimite();
                Integer idade = dadosClienteByCpf.getBody().getIdade();
                BigDecimal limiteAprovado = calcularLimiteAprovado(limite, idade);

                CartaoAprovadoDtoResponse cartaoAprovado = new CartaoAprovadoDtoResponse();
                cartaoAprovado.setCartao(cartao.getNome());
                cartaoAprovado.setBandeira(cartao.getBandeira());
                cartaoAprovado.setLimiteAprovado(limiteAprovado);

                return cartaoAprovado;
            }).collect(Collectors.toList());
            return new RetornoAvaliacaoCliente(cartoesAprovados);
        } catch (FeignException.FeignClientException err) {
            int status = err.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(err.getMessage(), status);
        }
    }

    private BigDecimal calcularLimiteAprovado(BigDecimal limite, Integer idade) {
        var multiplicacao = limite.multiply(BigDecimal.valueOf(idade));
        return multiplicacao.divide(BigDecimal.valueOf(10));
    }

}
