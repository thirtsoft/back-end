package com.spring.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "request_order")
public class RequestOrder extends CategoryOrder {

    @Column(name = "code")
    private String code;

    @Column(name = "numeroCommande", length = 70)
    private Long numeroCommande;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "montantReglement")
    private double montantReglement;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "typeReglement")
    private String typeReglement;

    private String status;

    private Date dateCommande;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestOrder")
    @JsonIgnore
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Utilisateur utilisateur;

   /* @OneToMany(mappedBy = "requestOrder", fetch = FetchType.LAZY)
    @Valid
    @JsonIgnore
    private List<Item> items = new ArrayList<>();*/

    /*
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client = new Client();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_address_id", referencedColumnName = "id")
    private Address toAddress = new Address();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_address_id", referencedColumnName = "id")
    private Address fromAddress = new Address();
    */

    /*@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;*/


    public void addItem(Item item) {
        items.add(item);
        item.setRequestOrder(this);
    }
}
