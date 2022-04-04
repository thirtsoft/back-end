package com.spring.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(name = "numero", length = 90)
    private Long numero;

    @Column(name = "image")
    private String img;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "productName")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "request_order_id")
    //  @JsonIgnore
    private RequestOrder requestOrder;
}
