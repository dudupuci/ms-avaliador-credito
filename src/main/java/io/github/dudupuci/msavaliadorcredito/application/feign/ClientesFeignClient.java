package io.github.dudupuci.msavaliadorcredito.application.feign;

import io.github.dudupuci.msavaliadorcredito.domain.exception.DadosClienteNotFoundException;
import io.github.dudupuci.msavaliadorcredito.domain.exception.ErroComunicacaoMicroservicesException;
import io.github.dudupuci.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClientesFeignClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> getDadosClienteByCpf(@RequestParam("cpf") String cpf);

}
