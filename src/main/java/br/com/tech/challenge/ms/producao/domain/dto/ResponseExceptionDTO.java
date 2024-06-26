package br.com.tech.challenge.ms.producao.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseExceptionDTO {

    private List<String> messages;
    private Integer statusCode;
    private String exceptionMessage;

}
