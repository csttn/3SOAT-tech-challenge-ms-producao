package br.com.tech.challenge.ms.producao.domain.entidades;


import lombok.*;

import java.util.Set;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Cliente {


    private Long id;

    private String nome;

    private String cpf;

    private String email;


    private Set<Pedido> pedido;

}
