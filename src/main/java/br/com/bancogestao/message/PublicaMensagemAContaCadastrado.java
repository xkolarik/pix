package br.com.bancogestao.message;

import br.com.bancogestao.entity.Conta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PublicaMensagemAContaCadastrado {

    private static final Logger log = LoggerFactory.getLogger(PublicaMensagemAContaCadastrado.class);

    public void publica(Conta contacadastrada) {
        log.info("publica mensagem de conta cadastrada");
    }
}
