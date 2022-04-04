package com.spring.restaurant.dto;

import com.spring.restaurant.model.Client;
import com.spring.restaurant.model.Item;
import com.spring.restaurant.model.RequestOrder;
import com.spring.restaurant.model.Utilisateur;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequest {

  //  private Client client;
    private Utilisateur utilisateur;
    private RequestOrder requestOrder;
    private List<Item> items;
    //   private User user;
    //  private Address fromAddress;
    //  private Address toAddress;

}