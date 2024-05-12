package br.com.tech.challenge.ms.producao.domain.entidades;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode()
public class FilaPedidos {


    private Integer senhaRetirada;

    private Integer idCliente;

    private String nomeCliente;

    private String statusPedido;

    private LocalDateTime dataHora;

}
