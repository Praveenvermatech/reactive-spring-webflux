package org.developervisits.dao;

import org.developervisits.entities.Societe;
import org.developervisits.entities.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction,String> {
  public Flux<Transaction> findBySociete(Societe societe);
}
