package br.me.desafio.challengeme.repositories;

import br.me.desafio.challengeme.entities.PedidoItem;
import br.me.desafio.challengeme.entities.pk.PedidoItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, PedidoItemPK> {

}
