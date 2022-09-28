package br.me.desafio.challengeme.repositories;

import br.me.desafio.challengeme.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
