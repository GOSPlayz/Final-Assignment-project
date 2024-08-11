package ecomersDatabase.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String productPrice;
	private String productDescription;
	private String productDimensions;
	private String productMaterialOne;
	private String productMaterialTwo;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
	Set<Orders> orderss = new HashSet<>();
	
	 
	    @PreRemove
	    private void removeOrdersAssociations() {
	        for (Orders orders : this.orderss) {
	            orders.getProducts().remove(this);
	        }
	    }
}
