package io.github.dudupuci.msavaliadorcredito.application.feign;

import io.github.dudupuci.msavaliadorcredito.application.presentation.CartaoDtoResponse;
import io.github.dudupuci.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes  ")
public interface CartoesFeignClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<CartaoDtoResponse>> getCartoesRendaAte(@RequestParam("renda") Long renda);
}
