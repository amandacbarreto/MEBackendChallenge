package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.DTO.PedidoDTO;
import br.me.desafio.challengeme.DTO.PedidoItemDTO;
import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.entities.Pedido;
import br.me.desafio.challengeme.entities.PedidoItem;
import br.me.desafio.challengeme.repositories.PedidoItemRepository;
import br.me.desafio.challengeme.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PedidoService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;


    public List<Pedido> findAll() {
        return repository.findAll();
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
