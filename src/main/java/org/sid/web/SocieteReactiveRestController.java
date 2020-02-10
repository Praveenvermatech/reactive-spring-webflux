package org.sid.web;

import java.time.Duration;
import java.util.List;

import org.sid.dao.SocieteRepository;
import org.sid.entities.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class SocieteReactiveRestController {
    @Autowired
    private SocieteRepository societeRepository;
    
    
    @GetMapping(value = "/societes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Societe> findAll(){
        return societeRepository.findAll().delayElements(Duration.ofMillis(5500));   // all element see here in given duration. 
    }
    
    @GetMapping(value = "/societes-buffer/{size}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Societe>> findAllWithBuffer(@PathVariable int size){
        return societeRepository.findAll().buffer(size);   // all element see here in bulk if given buffer. 
    }
    
    
    @GetMapping(value = "/societes/{id}")
    public Mono<Societe> getOne(@PathVariable String id){
        return societeRepository.findById(id);
    }
    @PostMapping("/societes")
    public Mono<Societe> save(@RequestBody Societe societe){
        return societeRepository.save(societe);
    }
    @DeleteMapping(value = "/societes/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return societeRepository.deleteById(id);
    }
    @PutMapping("/societes/{id}")
    public Mono<Societe> update(@RequestBody Societe societe, @PathVariable String id){
        societe.setId(id);
        return societeRepository.save(societe);
    }

}
