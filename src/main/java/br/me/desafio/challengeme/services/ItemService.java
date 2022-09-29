package br.me.desafio.challengeme.services;

import br.me.desafio.challengeme.entities.Item;
import br.me.desafio.challengeme.repositories.ItemRepository;
import br.me.desafio.challengeme.services.exceptions.DatabaseException;
import br.me.desafio.challengeme.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(Long id){
        Optional<Item> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Item insert (Item obj) {
        return repository.save(obj);
    }

    public void delete (Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Item update (Long id, Item obj){
        try{
            Item entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Item entity, Item obj) {
        entity.setDescricao(obj.getDescricao());
        entity.setPrecoUnitario(obj.getPrecoUnitario());
    }

}
