package br.com.tech.challenge.ms.producao.servicos;


import br.com.tech.challenge.ms.producao.api.client.PedidosClient;
import br.com.tech.challenge.ms.producao.api.exception.ObjectNotFoundException;
import br.com.tech.challenge.ms.producao.api.exception.StatusPedidoInvalidoException;
import br.com.tech.challenge.ms.producao.bd.repositorios.FilaPedidosRepository;
import br.com.tech.challenge.ms.producao.bd.repositorios.PedidoRepository;
import br.com.tech.challenge.ms.producao.domain.dto.ClienteDTO;
import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.ProdutoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.StatusPedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Pedido;
import br.com.tech.challenge.ms.producao.domain.entidades.Produto;
import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import br.com.tech.challenge.ms.producao.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {


    @Autowired
    FilaPedidosRepository filaPedidosRepository;


    private final ModelMapper mapper;

    private final PedidosClient pedidosClient;


    @Transactional
    public PedidoDTO updateStatus(Long pedidoId, StatusPedidoDTO novoStatus) {
        log.info("Atualizando status do pedido {} com novo status {}", pedidoId, novoStatus);


        validarStatusPedido(novoStatus.getStatusPedido());


        PedidoDTO pedidoDTO = pedidosClient.findById(pedidoId);

        if (pedidoDTO != null) {
            pedidoDTO.setStatusPedido(novoStatus.getStatusPedido());

            if (pedidoDTO.getStatusPedido() == StatusPedido.FINALIZADO || pedidoDTO.getStatusPedido() == StatusPedido.CANCELADO) {
                filaPedidosRepository.deleteById(String.valueOf(pedidoDTO.getId()));
                return pedidosClient.save(pedidoDTO);
            } else {
                Optional<FilaPedidosDTO> filaPedidosDTOOptional = filaPedidosRepository.findById(String.valueOf(pedidoDTO.getId()));

                if (filaPedidosDTOOptional.isPresent()) {
                    FilaPedidosDTO filaPedidosDTO = filaPedidosDTOOptional.get();
                    filaPedidosDTO.setStatusPedido(novoStatus.getStatusPedido());
                    FilaPedidosDTO pedidoEmFila = filaPedidosRepository.save(filaPedidosDTO);
                    pedidoEmFila.getStatusPedido();
                    return pedidoDTO;
                } else {
                    throw new ObjectNotFoundException("Pedido não encontrado na base do mongo");
                }
            }
        } else {
            throw new ObjectNotFoundException("Pedido não encontrado na api de pedidos");
        }
    }


    private void validarStatusPedido(StatusPedido statusPedido) {
        log.info("Validando se o status do pedido e valido");
        for (StatusPedido enumValue : StatusPedido.values()) {
            if (enumValue == statusPedido) {
                return;
            }
        }
        throw new StatusPedidoInvalidoException();
    }

    private ClienteDTO setClienteAnonimoDTO() {
        return ClienteDTO.builder()
                .id(99L)
                .nome("Usuario Anonimo")
                .email("")
                .cpf("999.999.999-99")
                .build();
    }

}
