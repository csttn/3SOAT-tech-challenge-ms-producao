package br.com.tech.challenge.ms.producao.domain.entidades;

import br.com.tech.challenge.ms.producao.domain.enums.Role;
import lombok.*;


@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario {


    private Long id;

    private String usuario;

    private String senha;


    private Role role;

}
