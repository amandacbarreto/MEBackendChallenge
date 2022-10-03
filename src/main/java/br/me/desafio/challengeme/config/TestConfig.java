package br.me.desafio.challengeme.config;

import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.entities.Pedido;
import br.me.desafio.challengeme.repositories.ItemRepository;
import br.me.desafio.challengeme.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        Pedido p1 = new Pedido(1L);
        Pedido p2 = new Pedido(2L);
        pedidoRepository.saveAll(Arrays.asList(p1,p2));
        Item i1 = new Item(null, "Item A", new BigDecimal(10),1,p1);
        Item i2 = new Item(null, "Item B", new BigDecimal(5),2,p1);
        itemRepository.saveAll(Arrays.asList(i1,i2));
    }
}
