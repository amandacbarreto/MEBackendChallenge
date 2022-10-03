package br.me.desafio.challengeme.resources;


import br.me.desafio.challengeme.DTO.StatusDTO;
import br.me.desafio.challengeme.DTO.StatusRespostaDTO;
import br.me.desafio.challengeme.entities.Status;
import br.me.desafio.challengeme.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/api/status")
public class StatusResource {

    @Autowired
    private StatusService service;

    @PostMapping
    public ResponseEntity<StatusRespostaDTO> insert(@RequestBody StatusDTO dto){
        StatusRespostaDTO status = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(status.getPedido()).toUri();
        return ResponseEntity.created(uri).body(status);
    }

    @PostMapping(value = "/2")
    public ResponseEntity<Status> insert2(@RequestBody StatusDTO dto){
        Status status = service.insert2(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(status.getPedido()).toUri();
        return ResponseEntity.created(uri).body(status);
    }

}