package br.me.desafio.challengeme.resources;


import br.me.desafio.challengeme.dto.StatusDTO;
import br.me.desafio.challengeme.dto.StatusRespostaDTO;
import br.me.desafio.challengeme.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/api/status")
public class StatusResource {

    @Autowired
    private StatusService service;

    @PostMapping
    public ResponseEntity<StatusRespostaDTO> insert(@Valid @RequestBody StatusDTO dto){
        StatusRespostaDTO status = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(status.getId()).toUri();
        return ResponseEntity.created(uri).body(status);
    }
}