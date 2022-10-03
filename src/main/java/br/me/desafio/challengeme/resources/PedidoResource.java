package br.me.desafio.challengeme.resources;

import br.me.desafio.challengeme.DTO.PedidoDTO;
import br.me.desafio.challengeme.DTO.PedidoRespostaDTO;
import br.me.desafio.challengeme.entities.Pedido;
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

    @GetMapping
    public ResponseEntity<List<PedidoRespostaDTO>> findAll() {
        List<PedidoRespostaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

   @GetMapping("/{id}")
    public ResponseEntity<PedidoRespostaDTO> findById(@PathVariable String id) {
        PedidoRespostaDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<PedidoRespostaDTO> insert(@RequestBody PedidoDTO dto){
        PedidoRespostaDTO pedido = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoRespostaDTO> update(@PathVariable String id, @RequestBody PedidoDTO obj) {
        PedidoRespostaDTO pedido = service.update(id, obj);
        return ResponseEntity.ok().body(pedido);
    }
}
