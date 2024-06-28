package br.com.bancogestao.service;

import br.com.bancogestao.message.PublicaMensagemAContaCadastrado;
import br.com.bancogestao.repository.ContaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private PublicaMensagemAContaCadastrado publicaMensagemAContaCadastrado;

    @InjectMocks
    private ContaService contaService;

    @Test
    @DisplayName("Salvar quando já existe um aluno com o mesmo email cadastrado")
    void teste() {

    }

    @Test
    void tesste1() {

    }
}
