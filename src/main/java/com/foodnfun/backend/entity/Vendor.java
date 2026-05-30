package com.foodnfun.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems;

    public Vendor() {}

    public Long   getId()                      { return id; }
    public void   setId(Long id)               { this.id = id; }
    public String getName()                    { return name; }
    public void   setName(String name)         { this.name = name; }
    public String getEmail()                   { return email; }
    public void   setEmail(String email)       { this.email = email; }
    public String getPhone()                   { return phone; }
    public void   setPhone(String phone)       { this.phone = phone; }
    public String getAddress()                 { return address; }
    public void   setAddress(String address)   { this.address = address; }
    public List<MenuItem> getMenuItems()       { return menuItems; }
    public void   setMenuItems(List<MenuItem> m){ this.menuItems = m; }
}
