package br.com.bancogestao.service;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.exception.ContaNaoEncontradaException;
import br.com.bancogestao.exception.SaldoInsuficienteException;
import br.com.bancogestao.message.PublicaMensagemAContaCadastrado;
import br.com.bancogestao.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public record ContaService(ContaRepository contaRepository,
                           PublicaMensagemAContaCadastrado publicaMensagemAContaCadastrado) {

    @Transactional
    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta buscarContaPorNumero(int numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }

    @Transactional
    public Conta realizarTransacao(int numeroConta, String formaPagamento, double valor) {
        Conta conta = buscarContaPorNumero(numeroConta);
        double taxa = calcularTaxa(formaPagamento, valor);
        if (conta.getSaldo() < valor + taxa) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo() - (valor + taxa));
        return contaRepository.save(conta);
    }

    private double calcularTaxa(String formaPagamento, double valor) {
        return switch (formaPagamento) {
            case "D" -> valor * 0.03;
            case "C" -> valor * 0.05;
            case "P" -> 0;
            default -> throw new IllegalArgumentException("Forma de pagamento inválida");
        };
    }
}
