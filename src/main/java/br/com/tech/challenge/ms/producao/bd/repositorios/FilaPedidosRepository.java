package br.com.tech.challenge.ms.producao.bd.repositorios;

import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilaPedidosRepository extends MongoRepository<FilaPedidosDTO, String> {
    void deleteAllById(Long id);
}

