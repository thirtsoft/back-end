package com.spring.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends PublicData {

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    private int qteStock;

    @Column(name = "image")
    private String img;

    @Column(name = "description")
    @Lob
    private String description;

  //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_Category")
    private Category category;

}
