package org.sid.web;

import java.time.Duration;
import java.util.List;

import org.sid.dao.SocieteRepository;
import org.sid.entities.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SocieteReactiveRestController {
   
	@Autowired
    private SocieteRepository societeRepository;      // Reactive DB driver here
    
    @GetMapping(value = "/societes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Societe> findAll(){													// List of Values	
        return societeRepository.findAll().delayElements(Duration.ofMillis(5500));  // all element see here in given duration 5sec. 
    }
    
    @GetMapping(value = "/societes-buffer/{size}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Societe>> findAllWithBuffer(@PathVariable int size){   // List of Values	
    	
        return societeRepository.findAll().buffer(size);            // collect all incoming element see here in single list. 
    }
    
    @GetMapping(value = "/societes/{id}")
    public Mono<Societe> getOne(@PathVariable String id){           // Single Values	
        return societeRepository.findById(id);
    }
    @PostMapping("/societes")
    public Mono<Societe> save(@RequestBody Societe societe){        // Single Values	
        return societeRepository.save(societe);
    }
    @DeleteMapping(value = "/societes/{id}")
    public Mono<Void> delete(@PathVariable String id){              // Single Values	
        return societeRepository.deleteById(id);
    }
    @PutMapping("/societes/{id}")
    public Mono<Societe> update(@RequestBody Societe societe, @PathVariable String id){      // Single Values	
        societe.setId(id);
        return societeRepository.save(societe);
    }

}
