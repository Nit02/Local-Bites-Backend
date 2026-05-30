package com.foodnfun.backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name="menu_items")
public class MenuItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "vendor_id") // foreign key column in menu_items table
    private Vendor vendor;

    // Constructors
    public MenuItem() {}

    public MenuItem(String name, Double price, Vendor vendor) {
        this.name = name;
        this.price = price;
        this.vendor = vendor;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
    
    
}
