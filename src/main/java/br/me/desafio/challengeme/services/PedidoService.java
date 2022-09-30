package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.DTO.PedidoDTO;
import br.me.desafio.challengeme.DTO.PedidoItemDTO;
import br.me.desafio.challengeme.DTO.PedidoItemRespostaDTO;
import br.me.desafio.challengeme.DTO.PedidoRespostaDTO;
import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.entities.Pedido;
import br.me.desafio.challengeme.entities.PedidoItem;
import br.me.desafio.challengeme.repositories.PedidoItemRepository;
import br.me.desafio.challengeme.repositories.PedidoRepository;
import br.me.desafio.challengeme.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

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
        pedido = this.savePedido(dto, pedido);
        return pedido.convertToPedidoRespostaDTO();
    }

    public PedidoRespostaDTO update (Long id, PedidoDTO dto){
        try{
            Optional<Pedido> obj = repository.findById(id);
            Pedido pedido = obj.get();

            for (PedidoItem pedidoItem: pedido.getItens()){
                pedidoItemRepository.delete(pedidoItem);
            }

            Set<PedidoItem> pedidoItens = new HashSet<>();
            for (PedidoItemDTO pedidoItem: dto.getItens()){
                Item i = itemService.findById(pedidoItem.getId());
                PedidoItem pi = new PedidoItem(pedido, i, pedidoItem.getQuantidade(), i.getPrecoUnitario());
                pedidoItens.add(pi);
            }
            pedido.setItens(pedidoItens);
            pedidoItemRepository.saveAll(pedidoItens);
            return repository.save(pedido).convertToPedidoRespostaDTO();

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Pedido savePedido (PedidoDTO dto, Pedido pedido){

        /*Set<PedidoItem> pedidoItens = new HashSet<>();
        for (PedidoItemDTO pedidoItem: dto.getItens()){
            Item i = itemService.findById(pedidoItem.getId());
            PedidoItem pi = new PedidoItem(pedido, i, pedidoItem.getQuantidade(), i.getPrecoUnitario());
            pedidoItens.add(pi);
        }
        pedido.setItens(pedidoItens);*/
        pedido = this.addItensToPedido(pedido, dto.getItens());
        repository.save(pedido);
        pedidoItemRepository.saveAll(pedido.getItens());
        return pedido;
    }

    public Pedido addItensToPedido (Pedido pedido, List<PedidoItemDTO> itensDTO) {
        Set<PedidoItem> pedidoItens = new HashSet<>();
        for (PedidoItemDTO pedidoItem: itensDTO){
            Item i = itemService.findById(pedidoItem.getId());
            PedidoItem pi = new PedidoItem(pedido, i, pedidoItem.getQuantidade(), i.getPrecoUnitario());
            pedidoItens.add(pi);
        }
        pedido.setItens(pedidoItens);
        return pedido;
    }



}
