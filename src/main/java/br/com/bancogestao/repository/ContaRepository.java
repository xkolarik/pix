package br.com.bancogestao.repository;

import br.com.bancogestao.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    boolean existsByEmail(String email);
}
