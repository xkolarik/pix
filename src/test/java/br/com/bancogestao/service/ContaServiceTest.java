package br.com.bancogestao.service;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.exception.ContaNaoEncontradaException;
import br.com.bancogestao.exception.SaldoInsuficienteException;
import br.com.bancogestao.repository.ContaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    @DisplayName("Teste criar conta com sucesso")
    void testCriarContaSucesso() {
        // Dado
        Conta conta = new Conta(1, 234, 100.0);
       // when(contaRepository.findByNumeroConta(Mockito.anyInt())).thenReturn(Optional.empty());
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        // Quando
        Conta contaSalva = contaService.criarConta(conta);

        // Então
        assertNotNull(contaSalva);
        assertEquals(conta.getNumeroConta(), contaSalva.getNumeroConta());
        verify(contaRepository, times(1)).save(conta);
    }

    @Test
    @DisplayName("Teste buscar conta por número existente")
    void testBuscarContaPorNumeroExistente() {
        // Dado
        Conta conta = new Conta(1, 234, 100.0);
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.of(conta));

        // Quando
        Conta contaEncontrada = contaService.buscarContaPorNumero(1);

        // Então
        assertNotNull(contaEncontrada);
        assertEquals(conta.getNumeroConta(), contaEncontrada.getNumeroConta());
        assertEquals(conta.getSaldo(), contaEncontrada.getSaldo());
        verify(contaRepository, times(1)).findByNumeroConta(1);
    }

    @Test
    @DisplayName("Teste buscar conta por número inexistente")
    void testBuscarContaPorNumeroInexistente() {
        // Dado
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.empty());

        // Quando e Então
        assertThrows(ContaNaoEncontradaException.class, () -> contaService.buscarContaPorNumero(1));
        verify(contaRepository, times(1)).findByNumeroConta(1);
    }

    @Test
    @DisplayName("Teste realizar transação com saldo insuficiente")
    void testRealizarTransacaoComSaldoInsuficiente() {
        // Dado
        Conta conta = new Conta(1, 234, 100.0);
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.of(conta));

        // Quando e Então
        assertThrows(SaldoInsuficienteException.class, () -> contaService.realizarTransacao(1, "D", 110.0));
        verify(contaRepository, times(1)).findByNumeroConta(1);
        verify(contaRepository, never()).save(conta);
    }
}