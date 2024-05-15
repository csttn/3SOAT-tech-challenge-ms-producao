package br.com.tech.challenge.servicos;

import br.com.tech.challenge.ms.producao.api.client.FilaPedidosClient;
import br.com.tech.challenge.ms.producao.api.client.PedidosClient;
import br.com.tech.challenge.ms.producao.bd.repositorios.FilaPedidosRepository;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import br.com.tech.challenge.ms.producao.servicos.FilaPedidosService;
import br.com.tech.challenge.ms.producao.servicos.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FilaPedidosServiceTest {

    @Mock
    private FilaPedidosRepository filaPedidosRepository;

    @Mock
    private FilaPedidosClient filaPedidosClient;

    @Mock
    private PedidosClient pedidosClient;

    @Mock
    private ModelMapper mapper;

    @Mock
    private FilaPedidosService filaPedidosService;

    FilaPedidosServiceTest() {
        MockitoAnnotations.openMocks(this);
        filaPedidosService = new FilaPedidosService(filaPedidosRepository, filaPedidosClient, pedidosClient, mapper);
    }

    @Test
    void testListFilaPedidos() {
        List<FilaPedidosDTO> mockPedidos = new ArrayList<>();
        mockPedidos.add(this.setFilaPedido());
        mockPedidos.add(this.setFilaPedidoCancelado());
        mockPedidos.add(this.setFilaPedidoEmPreparacao());
        mockPedidos.add(this.setFilaPedidoFinalizado());
        mockPedidos.add(this.setFilaPedidoPronto());

        when(pedidosClient.findAll(any())).thenReturn(new PageImpl<>(mockPedidos));
        when(filaPedidosRepository.saveAll(any())).thenReturn(mockPedidos);


        // Mocking external service calls

        List<FilaPedidosDTO> result = filaPedidosService.listFilaPedidos(0, 10);

        verify(filaPedidosRepository, times(1)).saveAll(anyList());

    }

    @Test
    void testListFilaPedidosWithStatusFilter() {
        List<FilaPedidosDTO> mockPedidos = new ArrayList<>();
        mockPedidos.add(this.setFilaPedido());
        mockPedidos.add(this.setFilaPedidoCancelado());
        mockPedidos.add(this.setFilaPedidoEmPreparacao());
        mockPedidos.add(this.setFilaPedidoFinalizado());
        mockPedidos.add(this.setFilaPedidoPronto());
        when(pedidosClient.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(mockPedidos));
        when(filaPedidosRepository.saveAll(any())).thenReturn(mockPedidos);

        List<FilaPedidosDTO> result = filaPedidosService.listFilaPedidos(1, 10);

        verify(filaPedidosRepository, times(1)).saveAll(anyList());

    }



    private FilaPedidosDTO setFilaPedido() {
        return FilaPedidosDTO.builder()
                .id("3")
                .senhaRetirada(123456)
                .statusPedido(StatusPedido.RECEBIDO)
                .nomeCliente("Cliente 3")
                .build();
    }

    private FilaPedidosDTO setFilaPedidoPronto() {
        return FilaPedidosDTO.builder()
                .id("3")
                .senhaRetirada(123456)
                .statusPedido(StatusPedido.RECEBIDO)
                .nomeCliente("Cliente 3")
                .build();
    }

    private FilaPedidosDTO setFilaPedidoEmPreparacao() {
        return FilaPedidosDTO.builder()
                .id("3")
                .senhaRetirada(123456)
                .statusPedido(StatusPedido.RECEBIDO)
                .nomeCliente("Cliente 3")
                .build();
    }

    private FilaPedidosDTO setFilaPedidoFinalizado() {
        return FilaPedidosDTO.builder()
                .id("3")
                .senhaRetirada(123456)
                .statusPedido(StatusPedido.FINALIZADO)
                .nomeCliente("Cliente 3")
                .build();
    }

    private FilaPedidosDTO setFilaPedidoCancelado() {
        return FilaPedidosDTO.builder()
                .id("3")
                .senhaRetirada(123456)
                .statusPedido(StatusPedido.CANCELADO)
                .nomeCliente("Cliente 3")
                .build();
    }
}
