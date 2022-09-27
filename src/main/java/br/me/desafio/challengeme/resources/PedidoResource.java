package br.me.desafio.challengeme.resources;

import br.me.desafio.challengeme.entities.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/pedido")
public class PedidoResource {

    @GetMapping
    public ResponseEntity<Pedido> findAll() {
        Pedido p = new Pedido(1L);
        return ResponseEntity.ok().body(p);
    }
}
