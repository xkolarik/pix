package br.com.bancogestao.controller;


import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.exception.SaldoInsuficienteException;
import br.com.bancogestao.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final ContaService contaService;

    public TransacaoController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<?> realizarTransacao(@RequestBody TransacaoRequest request) {
        try {
            Conta contaAtualizada = contaService.realizarTransacao(
                    request.numero_conta(),
                    request.forma_pagamento(),
                    request.valor()
            );
            return new ResponseEntity<>(contaAtualizada, HttpStatus.CREATED);
        } catch (SaldoInsuficienteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private record TransacaoRequest(String forma_pagamento, int numero_conta, double valor) {
    }
}






