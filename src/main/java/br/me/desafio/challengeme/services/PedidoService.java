package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.DTO.ItemDTO;
import br.me.desafio.challengeme.DTO.PedidoDTO;
import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.entities.Pedido;
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

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(String id){
        try{
            Optional<Pedido> obj = repository.findById(id);
            Pedido pedido = obj.get();
            return pedido;
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException(id);
        }
    }


    public Pedido insert (PedidoDTO dto) {
        Pedido pedido = new Pedido(dto.getId());
        pedido = this.addItensToPedido(pedido, dto.getItens());
        repository.save(pedido);
        return pedido;
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

    public Pedido update (String id, PedidoDTO dto) {
        try{
            Pedido pedido = repository.getReferenceById(id);
            pedido.getItens().clear();
            pedido = this.addItensToPedido(pedido, dto.getItens());
            return repository.save(pedido);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Pedido addItensToPedido (Pedido pedido, List<ItemDTO> dto) {
        for (ItemDTO i : dto) {
            Item item = new Item(null, i.getDescricao(), i.getPrecoUnitario(), i.getQtd(), pedido);
            pedido.getItens().add(item);
        }
        return pedido;
    }
}
