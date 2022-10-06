package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.dto.*;
import br.me.desafio.challengeme.entities.Pedido;
import br.me.desafio.challengeme.entities.Status;
import br.me.desafio.challengeme.enums.StatusPedido;
import br.me.desafio.challengeme.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatusService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public StatusRespostaDTO insert (StatusDTO dto) {
        Optional<Pedido> obj = pedidoRepository.findById(dto.getId());
        if (obj.isEmpty()) {
            return new StatusRespostaDTO(dto.getId(), List.of(StatusPedido.CODIGO_PEDIDO_INVALIDO));
        }
        Pedido pedido = obj.get();
        Status status = new Status(dto.getItensAprovados(),dto.getValorAprovado(),pedido);
        status.checkStatus(dto.getStatus());
        return status.convertToDTO();
    }
}