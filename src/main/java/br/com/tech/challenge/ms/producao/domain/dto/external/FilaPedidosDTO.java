package br.com.tech.challenge.ms.producao.domain.dto.external;

import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Document(collection = "fila-de-pedidos")
public class FilaPedidosDTO {

    @Schema(description = "Senha para retirada do pedido")
    private Integer senhaRetirada;

    @Schema(description = "Nome do Cliente")
    private String nomeCliente;

    @Schema(description = "Status atual do pedido")
    private StatusPedido statusPedido;

    @Schema(description = "Id")
    @Id
    private String id;
}
