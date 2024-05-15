package br.com.tech.challenge.api;

import br.com.tech.challenge.ms.producao.api.PedidoController;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.StatusPedidoDTO;
import br.com.tech.challenge.ms.producao.servicos.FilaPedidosService;
import br.com.tech.challenge.ms.producao.servicos.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private FilaPedidosService filaPedidosService;

    @Mock
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListFilaPedidos() {
        List<FilaPedidosDTO> filaPedidosDTOList = List.of(
                new FilaPedidosDTO(), new FilaPedidosDTO()
        );
        when(filaPedidosService.listFilaPedidos(anyInt(), anyInt())).thenReturn(filaPedidosDTOList);

        ResponseEntity<List<FilaPedidosDTO>> responseEntity = pedidoController.listFilaPedidos(0, 100);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(filaPedidosService, times(1)).listFilaPedidos(anyInt(), anyInt());
    }

    @Test
    void testUpdateStatus() {
        StatusPedidoDTO statusPedidoDTO = new StatusPedidoDTO();
        PedidoDTO pedidoDTO = new PedidoDTO();

        when(pedidoService.updateStatus(anyLong(), any(StatusPedidoDTO.class))).thenReturn(pedidoDTO);

        ResponseEntity<PedidoDTO> responseEntity = pedidoController.updateStatus(1L, statusPedidoDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(pedidoService, times(1)).updateStatus(anyLong(), any(StatusPedidoDTO.class));
    }
}
