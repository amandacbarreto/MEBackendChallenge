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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;


    /*public List<Pedido> findAll() {
        return repository.findAll();
    }*/

    public List<PedidoRespostaDTO> findAll() {
        List<Pedido> pedidos = repository.findAll();
        List<PedidoRespostaDTO>  pedidosDTO = new ArrayList<>();

        for (Pedido pedido : pedidos){
            List<PedidoItemRespostaDTO>  pedidoItens = new ArrayList<>();
            for (PedidoItem item : pedido.getItens()){
                PedidoItemRespostaDTO pi = new PedidoItemRespostaDTO(item.getItem().getDescricao(), item.getItem().getPrecoUnitario(), item.getQuantidade());
                pedidoItens.add(pi);
            }
            PedidoRespostaDTO pedidoDTO = new PedidoRespostaDTO(pedido.getId(), pedidoItens);
            pedidosDTO.add(pedidoDTO);
        }

        return pedidosDTO;
    }

    public Pedido findById(Long id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.get();
    }

    public Pedido insert (PedidoDTO dto) {

        Pedido pedido = new Pedido();
        Set<PedidoItem> pedidoItens = new HashSet<>();

        for (PedidoItemDTO pedidoItem: dto.getItens()){
            Item i = itemService.findById(pedidoItem.getId());
            PedidoItem pi = new PedidoItem(pedido, i, pedidoItem.getQuantidade(), i.getPrecoUnitario());
            pedidoItens.add(pi);
        }
        pedido.setItens(pedidoItens);
        repository.save(pedido);
        pedidoItemRepository.saveAll(pedidoItens);
        return pedido;
    }

    /*public Pedido insert (Pedido pedido) {
        return repository.save(pedido);
    }*/


}
