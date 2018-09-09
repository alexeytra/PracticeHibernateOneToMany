package com.hibirnate.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;

    @Column(name = "username")
    private String username;


    @OneToMany(mappedBy = "cart")
    private Set<Items> items;

    public Cart() {
    }


    public long getId() {
        return id;
    }

    public Set<Items> getItems() {
        return items;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
