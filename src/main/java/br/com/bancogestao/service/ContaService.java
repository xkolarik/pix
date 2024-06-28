package br.com.bancogestao.service;

import br.com.bancogestao.entity.Conta;
import br.com.bancogestao.message.PublicaMensagemAContaCadastrado;
import br.com.bancogestao.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public record ContaService(ContaRepository contaRepository,
                           PublicaMensagemAContaCadastrado publicaMensagemAContaCadastrado) {

    public Conta salvar(Conta conta) {
        return conta;
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id).orElse(null);
    }
}
