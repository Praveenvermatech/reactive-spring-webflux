package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
@Document
@Data
public class Transaction {
    @Id
    private String id;
    private Instant instant;
    private double price;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Societe societe;
    
    public Transaction() {
		// TODO Auto-generated constructor stub
	}
    
	public String getId() {
		return id;
	}
	public Instant getInstant() {
		return instant;
	}
	public double getPrice() {
		return price;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setInstant(Instant instant) {
		this.instant = instant;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	public Transaction(String id, Instant instant, double price, Societe societe) {
		super();
		this.id = id;
		this.instant = instant;
		this.price = price;
		this.societe = societe;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", instant=" + instant + ", price=" + price + ", societe=" + societe + "]";
	}
    
    
    
    
}
