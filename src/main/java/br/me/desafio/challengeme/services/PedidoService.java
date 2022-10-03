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
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

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

    public PedidoRespostaDTO findById(Long id){
        try{
            Optional<Pedido> obj = repository.findById(id);
            Pedido pedido = obj.get();
            return pedido.convertToPedidoRespostaDTO();
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException(id);
        }
    }


    public PedidoRespostaDTO insert (PedidoDTO dto) {

        Pedido pedido = new Pedido();
        pedido = this.addItensToPedido(pedido, dto.getItens());
        repository.save(pedido);
        itemRepository.saveAll(pedido.getItens());
        return pedido.convertToPedidoRespostaDTO();
    }

    public void delete (Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public PedidoRespostaDTO update (Long id, PedidoDTO dto) {
        try{
            Pedido pedido = repository.getReferenceById(id);
            pedido.getItens().clear();

            for (ItemDTO itemDTO : dto.getItens()) {
                Item i = new Item(null, itemDTO.getDescricao(), itemDTO.getPrecoUnitario(), itemDTO.getQuantidade(),pedido);
                pedido.getItens().add(i);
            }

            return repository.save(pedido).convertToPedidoRespostaDTO();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Pedido addItensToPedido (Pedido pedido, Set<ItemDTO> dto) {
        for (ItemDTO itemDTO : dto) {
            Item i = new Item(null, itemDTO.getDescricao(), itemDTO.getPrecoUnitario(), itemDTO.getQuantidade(),pedido);
            pedido.getItens().add(i);
        }
        return pedido;
        /*Set<Item> pedidoItens = new HashSet<>();
        for (ItemDTO pedidoItem: itensDTO){
            Item i = new Item(null, pedidoItem.getDescricao(), pedidoItem.getPrecoUnitario(), pedidoItem.getQuantidade(),pedido);
            pedidoItens.add(i);
        }
        pedido.setItens(pedidoItens);
        return pedido;*/
    }
}
