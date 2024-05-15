package br.com.tech.challenge.ms.producao.domain.entidades;

import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pedido {


    private Long id;

    private Integer senhaRetirada;


    private Cliente cliente;

    private List<Produto> produtos;

    private BigDecimal valorTotal;


    private StatusPedido statusPedido;

    private Pagamento pagamento;


    private LocalDateTime dataHora;

}
