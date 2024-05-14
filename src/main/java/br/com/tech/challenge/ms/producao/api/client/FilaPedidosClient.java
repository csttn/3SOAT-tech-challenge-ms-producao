package br.com.tech.challenge.ms.producao.api.client;



import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fila", url = "${pedidos.api.url}")
public interface FilaPedidosClient {

    @GetMapping("/pedidos")
    Page<FilaPedidosDTO> findAll(Pageable pageable);
}
