package br.com.tech.challenge.ms.producao.servicos;


import br.com.tech.challenge.ms.producao.api.client.ClienteClient;
import br.com.tech.challenge.ms.producao.api.exception.ObjectNotFoundException;
import br.com.tech.challenge.ms.producao.domain.dto.ClienteCheckInDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteClient clienteClient;

    private final ModelMapper mapper;


    @Transactional
    public ClienteCheckInDTO checkInCliente(String cpf) {
        log.info("Encontrado o cliente por cpf {}", cpf);

        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteClient.checkInCliente(new ClienteCheckInDTO(cpf))
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado")));
        log.info("Mapeando entidade Cliente para ClienteCheckInDTO");
        return mapper.map(clienteOptional.get(), ClienteCheckInDTO.class);
    }


}
