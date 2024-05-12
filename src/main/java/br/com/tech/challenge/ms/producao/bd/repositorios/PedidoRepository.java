package br.com.tech.challenge.ms.producao.bd.repositorios;

import br.com.tech.challenge.ms.producao.domain.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
