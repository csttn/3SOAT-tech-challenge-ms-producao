package br.com.tech.challenge.servicos;

import br.com.tech.challenge.ms.producao.api.client.PedidosClient;
import br.com.tech.challenge.ms.producao.api.exception.ObjectNotFoundException;
import br.com.tech.challenge.ms.producao.api.exception.StatusPedidoInvalidoException;
import br.com.tech.challenge.ms.producao.bd.repositorios.FilaPedidosRepository;
import br.com.tech.challenge.ms.producao.domain.dto.ClienteDTO;
import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.ProdutoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.StatusPedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Categoria;
import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import br.com.tech.challenge.ms.producao.servicos.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.modelmapper.ModelMapper;

class PedidoServiceTest {

    @Mock
    private FilaPedidosRepository filaPedidosRepository;

    @Mock
    private PedidosClient pedidosClient;

    @Mock
    private final PedidoService pedidoService;

    @Mock
    private ModelMapper mapper;

    PedidoServiceTest() {
        MockitoAnnotations.openMocks(this);
        pedidoService = new PedidoService(filaPedidosRepository,mapper, pedidosClient);
    }

    @Test
    void testUpdateStatusPedidoEmPreparacao() {
        // Mocking data
        Long pedidoId = 1L;
        StatusPedidoDTO novoStatus = new StatusPedidoDTO(StatusPedido.EM_PREPARACAO);
        PedidoDTO pedidoDTO = this.setPedidoDTO();
        pedidoDTO.setId(pedidoId);


        FilaPedidosDTO filaPedidosDTO = this.setFilaPedido();
        filaPedidosDTO.setStatusPedido(novoStatus.getStatusPedido());

        // Mocking external service calls
        when(pedidosClient.findById(pedidoId)).thenReturn(pedidoDTO);
        when(filaPedidosRepository.findById(any())).thenReturn(Optional.ofNullable(this.setFilaPedido()));
        doNothing().when(filaPedidosRepository).deleteById(any());
        when(filaPedidosRepository.save(any())).thenReturn(filaPedidosDTO);

        // Calling the method to be tested
        PedidoDTO result = pedidoService.updateStatus(pedidoId, novoStatus);

        // Assertions
        assertEquals(StatusPedido.EM_PREPARACAO, result.getStatusPedido());
    }

    @Test
    void testUpdateStatusPedidoCancelado() {
        // Mocking data
        Long pedidoId = 1L;
        StatusPedidoDTO novoStatus = new StatusPedidoDTO(StatusPedido.CANCELADO);
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoId);


        FilaPedidosDTO filaPedidosDTO = this.setFilaPedido();
        filaPedidosDTO.setStatusPedido(novoStatus.getStatusPedido());

        // Mocking external service calls
        when(pedidosClient.findById(pedidoId)).thenReturn(pedidoDTO);
        when(pedidosClient.save(pedidoDTO)).thenReturn(pedidoDTO);
        when(filaPedidosRepository.findById(any())).thenReturn(Optional.ofNullable(this.setFilaPedido()));
        doNothing().when(filaPedidosRepository).deleteById(any());
        when(filaPedidosRepository.save(any())).thenReturn(filaPedidosDTO);


        // Calling the method to be tested
        PedidoDTO result = pedidoService.updateStatus(pedidoId, novoStatus);

        // Assertions
        assertEquals(StatusPedido.CANCELADO, result.getStatusPedido());
    }

    @Test
    void testAtualizaStatusDePedidoQueNaoExisteNaBase() {
        // Mocking data
        Long pedidoId = 1L;
        StatusPedidoDTO novoStatus = new StatusPedidoDTO(StatusPedido.EM_PREPARACAO);
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoId);


        FilaPedidosDTO filaPedidosDTO = this.setFilaPedido();
        filaPedidosDTO.setStatusPedido(novoStatus.getStatusPedido());

        // Mocking external service calls
        when(pedidosClient.findById(pedidoId)).thenReturn(pedidoDTO);
        when(pedidosClient.save(pedidoDTO)).thenReturn(pedidoDTO);
        when(filaPedidosRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(filaPedidosRepository).deleteById(any());
        when(filaPedidosRepository.save(any())).thenReturn(filaPedidosDTO);


        // Assertions
        assertThrows(ObjectNotFoundException.class, () -> pedidoService.updateStatus(pedidoId, novoStatus));
    }


    @Test
    void testUpdateStatusPedidoNotFound() {
        // Mocking data
        Long pedidoId = 1L;
        StatusPedidoDTO novoStatus = new StatusPedidoDTO(StatusPedido.FINALIZADO);

        // Mocking external service calls
        when(pedidosClient.findById(pedidoId)).thenReturn(null);

        // Assertions
        assertThrows(ObjectNotFoundException.class, () -> pedidoService.updateStatus(pedidoId, novoStatus));
        verifyNoInteractions(filaPedidosRepository);
    }

    @Test
    void testUpdateStatusPedidoInvalido() {
        // Mocking data
        Long pedidoId = 1L;
        StatusPedidoDTO novoStatus = new StatusPedidoDTO();

        // Mocking external service calls
        when(pedidosClient.findById(pedidoId)).thenReturn(null);

        // Assertions
        assertThrows(StatusPedidoInvalidoException.class, () -> pedidoService.updateStatus(pedidoId, novoStatus));
        verifyNoInteractions(filaPedidosRepository);
    }


    private FilaPedidosDTO setFilaPedido() {
        return FilaPedidosDTO.builder()
                .id("3")
                .senhaRetirada(123456)
                .statusPedido(StatusPedido.RECEBIDO)
                .nomeCliente("Cliente 3")
                .build();
    }

    private PedidoDTO setPedidoDTO() {
        return PedidoDTO.builder()
                .id(1L)
                .senhaRetirada(123456)
                .cliente(setClienteDTO())
                .produtos(List.of(setProdutoDTO()))
                .valorTotal(BigDecimal.valueOf(5.00))
                .statusPedido(StatusPedido.RECEBIDO)
                .build();
    }

    private ClienteDTO setClienteDTO() {
        return ClienteDTO.builder()
                .id(1L)
                .nome("Anthony Samuel Joaquim Teixeira")
                .email("anthony.samuel.teixeira@said.adv.br")
                .cpf("143.025.400-95")
                .build();
    }

    private ProdutoDTO setProdutoDTO() {
        return ProdutoDTO.builder()
                .id(1L)
                .descricao("Coca Cola")
                .valorUnitario(BigDecimal.valueOf(5.00))
                .categoria(setCategoria())
                .build();
    }

    private Categoria setCategoria() {
        return Categoria.builder()
                .id(2L)
                .descricao("Bebida")
                .build();
    }



}
