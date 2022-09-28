package br.me.desafio.challengeme.resources;

import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.entities.Pedido;
import br.me.desafio.challengeme.entities.PedidoItem;
import br.me.desafio.challengeme.models.PedidoItemRequestModel;
import br.me.desafio.challengeme.models.PedidoRequestModel;
import br.me.desafio.challengeme.repositories.PedidoItemRepository;
import br.me.desafio.challengeme.services.ItemService;
import br.me.desafio.challengeme.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(value="/api/pedido")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @Autowired
    private ItemService pedidoService;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody PedidoRequestModel pedidoRequest){
        Pedido pedido = new Pedido();
        Set<PedidoItem> itens = new HashSet<>();

        for (PedidoItemRequestModel item: pedidoRequest.getItens()){
            Item i = pedidoService.findById(item.getId());
            PedidoItem pi = new PedidoItem(pedido, i, item.getQuantidade(), i.getPrecoUnitario());
            itens.add(pi);
        }
        pedido.setItens(itens);
        pedido = service.insert(pedido);
        pedidoItemRepository.saveAll(itens);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }
}
