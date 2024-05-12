package br.com.tech.challenge.ms.producao.api.client;

import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "pedidos", url = "http://localhost:8080") // Ajuste a URL conforme necessário
public interface PedidosClient {

    @GetMapping("/pedidos")
    Page<FilaPedidosDTO> findAll(Pageable pageable);


    @GetMapping("/pedidos/{pedidoID}")
    PedidoDTO findById(@PathVariable("pedidoID") Long pedidoID);


    @PutMapping("/pedidos")
    PedidoDTO save(@RequestBody PedidoDTO pedidoDTO);
}
