package io.github.dudupuci.msavaliadorcredito.application;

import io.github.dudupuci.msavaliadorcredito.application.dtos.DadosAvaliacaoRequest;
import io.github.dudupuci.msavaliadorcredito.application.services.AvaliadorCreditoService;
import io.github.dudupuci.msavaliadorcredito.domain.exception.DadosClienteNotFoundException;
import io.github.dudupuci.msavaliadorcredito.domain.exception.ErroComunicacaoMicroservicesException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoResource {

    private final AvaliadorCreditoService service;

    @GetMapping
    public String status() {
        return "avaliador de credito";
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoRequest dto){
        try {
            var retornoAvaliacaoCliente = this.service.realizarAvaliacao(dto);
            return ResponseEntity.ok().body(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException err) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException err) {
            return ResponseEntity.status(HttpStatus.resolve(err.getStatus())).body(err.getMessage());
        }
    }

    @GetMapping(value = "/situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            var situacaoCliente = this.service.obterSituacaoCliente(cpf);
            return ResponseEntity.ok().body(situacaoCliente);

        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
