package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Societe {
    @Id
    private String id;
    private String name;
    private double price;
    
    public Societe() {
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public String toString() {
		return "Societe [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	public Societe(String id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
    
    
}
