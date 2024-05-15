package br.com.tech.challenge.ms.producao.domain.entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Categoria {


    private Long id;

    private String descricao;


    private Set<Produto> produtos;

}
