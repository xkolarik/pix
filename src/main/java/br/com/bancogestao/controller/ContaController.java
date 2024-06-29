package br.com.bancogestao.controller;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.exception.ContaJaExistenteException;
import br.com.bancogestao.exception.ContaNaoEncontradaException;
import br.com.bancogestao.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping()
    public ResponseEntity<?> criarConta(@RequestBody Conta conta) {
        try {
            Conta contaSalva = contaService.criarConta(conta);
            return new ResponseEntity<>(contaSalva, HttpStatus.CREATED);
        } catch (ContaJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta já cadastrada com este número de conta");
        }
    }

    @Operation(description = "Busca a conta pelo número da conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta encontrada"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @GetMapping
    public ResponseEntity<Conta> buscarContaPorNumero(@RequestParam("numero_conta") int numeroConta) {
        try {
            Conta conta = contaService.buscarContaPorNumero(numeroConta);
            return ResponseEntity.ok(conta);
        } catch (ContaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}