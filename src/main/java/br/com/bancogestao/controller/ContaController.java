package br.com.bancogestao.controller;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public record ContaController(ContaService contaService) {

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        Conta contaSalva = contaService.criarConta(conta);
        return new ResponseEntity<>(contaSalva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Conta> buscarContaPorNumero(@RequestParam int numeroConta) {
        Conta conta = contaService.buscarContaPorNumero(numeroConta);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }
}
