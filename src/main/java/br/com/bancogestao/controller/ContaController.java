package br.com.bancogestao.controller;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public record ContaController(ContaService contaService) {

    @PostMapping
    public Conta salvar(@RequestBody Conta conta) {
        return contaService.salvar(conta);
    }

    @Operation(description = "Busca o conta pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a conta"),
            @ApiResponse(responseCode = "400", description = "Não existe a conta com o id informado")
    })
    @GetMapping("/{id}")
    public Conta buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }
}
