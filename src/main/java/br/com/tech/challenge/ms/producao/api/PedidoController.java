package br.com.tech.challenge.ms.producao.api;


import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.StatusPedidoDTO;
import br.com.tech.challenge.ms.producao.servicos.FilaPedidosService;
import br.com.tech.challenge.ms.producao.servicos.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para controle de Pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    private final FilaPedidosService filaPedidosService;

    private final ModelMapper mapper;


    @Operation(summary = "Lista a fila de pedidos", description = "Endpoint para listagem da fila de pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fila de pedidos retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping(value = "/fila")
    public ResponseEntity<List<FilaPedidosDTO>> listFilaPedidos(
            @RequestParam(name = "pagina", defaultValue = "0") int pagina,
            @RequestParam(name = "tamanho", defaultValue = "100") int tamanho) {
        return ResponseEntity.ok().body(filaPedidosService.listFilaPedidos(pagina, tamanho));
    }

    @Operation(description = "Endpoint para atualizar status de um pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido atualizado com sucesso.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Status inválido"),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    }
    )
    @PutMapping("/{pedidoId}/status")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable Long pedidoId, @RequestBody StatusPedidoDTO statusPedidoDTO) {

        return ResponseEntity.ok(pedidoService.updateStatus(pedidoId, statusPedidoDTO));
    }

}
