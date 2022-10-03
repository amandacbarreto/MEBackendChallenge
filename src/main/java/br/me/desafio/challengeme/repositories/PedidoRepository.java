package br.me.desafio.challengeme.repositories;

import br.me.desafio.challengeme.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
