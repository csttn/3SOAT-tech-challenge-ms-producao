package br.com.tech.challenge.ms.producao.api.client;



import br.com.tech.challenge.ms.producao.domain.dto.ClienteCheckInDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Cliente;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Optional;

@FeignClient(name = "cliente-api", url = "${pedidos.api.url}")
public interface ClienteClient {
    @PostMapping("/clientes/check-in")
    Optional<Cliente> checkInCliente(@RequestBody ClienteCheckInDTO request);
}


