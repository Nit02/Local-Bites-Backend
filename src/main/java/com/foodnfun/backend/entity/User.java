package com.foodnfun.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    // CUSTOMER or VENDOR or ADMIN
    @Column(nullable = false)
    private String role = "CUSTOMER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
        name = "user_bookmarks",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "vendor_id")
    )
    private List<Vendor> bookmarks;

    // ── Getters & Setters ────────────────────────────────────────────────────
    public Long   getId()                     { return id; }
    public void   setId(Long id)              { this.id = id; }
    public String getEmail()                  { return email; }
    public void   setEmail(String email)      { this.email = email; }
    public String getName()                   { return name; }
    public void   setName(String name)        { this.name = name; }
    public String getPassword()               { return password; }
    public void   setPassword(String pw)      { this.password = pw; }
    public String getRole()                   { return role; }
    public void   setRole(String role)        { this.role = role; }
    public List<Review>  getReviews()         { return reviews; }
    public void   setReviews(List<Review> r)  { this.reviews = r; }
    public List<Vendor>  getBookmarks()       { return bookmarks; }
    public void   setBookmarks(List<Vendor> b){ this.bookmarks = b; }
}
