package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.DTO.ItemDTO;
import br.me.desafio.challengeme.DTO.PedidoDTO;
import br.me.desafio.challengeme.DTO.PedidoRespostaDTO;
import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.entities.Pedido;
import br.me.desafio.challengeme.repositories.ItemRepository;
import br.me.desafio.challengeme.repositories.PedidoRepository;
import br.me.desafio.challengeme.services.exceptions.DatabaseException;
import br.me.desafio.challengeme.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoRespostaDTO> findAll() {
        List<Pedido> pedidos = repository.findAll();
        List<PedidoRespostaDTO>  pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidos){
            pedidosDTO.add(pedido.convertToPedidoRespostaDTO());
        }
        return pedidosDTO;
    }

    public PedidoRespostaDTO findById(String id){
        try{
            Optional<Pedido> obj = repository.findById(id);
            Pedido pedido = obj.get();
            return pedido.convertToPedidoRespostaDTO();
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException(id);
        }
    }


    public PedidoRespostaDTO insert (PedidoDTO dto) {
        Pedido pedido = new Pedido(dto.getId());
        pedido = this.addItensToPedido(pedido, dto.getItens());
        repository.save(pedido);
        return pedido.convertToPedidoRespostaDTO();
    }

    public void delete (String id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public PedidoRespostaDTO update (String id, PedidoDTO dto) {
        try{
            Pedido pedido = repository.getReferenceById(id);
            pedido.getItens().clear();
            pedido = this.addItensToPedido(pedido, dto.getItens());
            return repository.save(pedido).convertToPedidoRespostaDTO();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Pedido addItensToPedido (Pedido pedido, Set<ItemDTO> dto) {
        for (ItemDTO itemDTO : dto) {
            Item i = new Item(null, itemDTO.getDescricao(), itemDTO.getPrecoUnitario(), itemDTO.getQtd(),pedido);
            pedido.getItens().add(i);
        }
        return pedido;
    }
}
