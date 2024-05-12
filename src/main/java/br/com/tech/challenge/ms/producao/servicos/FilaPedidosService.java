package br.com.tech.challenge.ms.producao.servicos;


import br.com.tech.challenge.ms.producao.api.client.FilaPedidosClient;
import br.com.tech.challenge.ms.producao.api.client.PedidosClient;
import br.com.tech.challenge.ms.producao.bd.repositorios.FilaPedidosRepository;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilaPedidosService {



   private final FilaPedidosRepository filaPedidosRepository;

    private final FilaPedidosClient filaPedidosClient;

    private final PedidosClient pedidosClient;

    private final ModelMapper mapper;


    public List<FilaPedidosDTO> listFilaPedidos(final Integer pagina, final Integer tamanho) {
        log.info("Listando fila de pedidos com pagina {} e tamanho {}", pagina, tamanho);

        final var pageable = PageRequest.of(pagina, tamanho, Sort.by("dataHora").descending());

        List<FilaPedidosDTO> listaFilaPedidos = pedidosClient.findAll(pageable).getContent();


        List<FilaPedidosDTO> pedidosFiltrados = listaFilaPedidos.stream()
                .filter(pedido ->
                        pedido.getStatusPedido().equals(StatusPedido.RECEBIDO) ||
                                pedido.getStatusPedido().equals(StatusPedido.EM_PREPARACAO) ||
                                pedido.getStatusPedido().equals(StatusPedido.PRONTO)
                )
                .sorted(Comparator.comparing(FilaPedidosDTO::getSenhaRetirada))
                .collect(Collectors.toList());

        filaPedidosRepository.saveAll(pedidosFiltrados);

        return pedidosFiltrados;
    }

}
