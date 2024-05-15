package br.com.tech.challenge.ms.producao.domain.entidades;

import br.com.tech.challenge.ms.producao.domain.enums.StatusPagamento;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pagamento {


    private Long id;


    private Pedido pedido;

    private LocalDateTime dataHoraPagamento;

    private BigDecimal valorTotal;

    private String qrData;


    private StatusPagamento statusPagamento;

}
