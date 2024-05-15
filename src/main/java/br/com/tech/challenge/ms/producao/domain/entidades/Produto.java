package br.com.tech.challenge.ms.producao.domain.entidades;



import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Produto {


    private Long id;

    private String descricao;


    private Categoria categoria;

    private BigDecimal valorUnitario;


    private List<Pedido> pedidos;

}
