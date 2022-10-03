package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.DTO.*;
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
        Optional<Pedido> obj = pedidoRepository.findById(dto.getPedido());
        Pedido pedido = obj.get();
        Set<StatusPedido> statusList = new HashSet<>();
        Status status = new Status(dto.getItensAprovados(),dto.getValorAprovado(),pedido,dto.getStatus());
        for(StatusPedido s : status.checkStatus(dto.getStatus())){
            statusList.add(s);
        }
        status.setStatus(statusList);
        return status.convertToStatusRespostaDTO();
    }
}