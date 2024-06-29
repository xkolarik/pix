package br.com.bancogestao.service;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.exception.ContaJaExistenteException;
import br.com.bancogestao.exception.ContaNaoEncontradaException;
import br.com.bancogestao.exception.SaldoInsuficienteException;
import br.com.bancogestao.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContaService {

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Transactional
    public Conta criarConta(Conta conta) {
        logger.info("Criando conta: {}", conta);

        if (contaRepository.findByNumeroConta(conta.getNumeroConta()).isPresent()) {
            throw new ContaJaExistenteException("Já existe uma conta com este número de conta");
        }
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
