package br.com.bancogestao.controller;


import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public record TransacaoController(ContaService contaService) {

    @PostMapping
    public ResponseEntity<Conta> realizarTransacao(@RequestBody TransacaoRequest request) {
        Conta contaAtualizada = contaService.realizarTransacao(request.numeroConta(), request.formaPagamento(), request.valor());
        return new ResponseEntity<>(contaAtualizada, HttpStatus.CREATED);
    }

}







