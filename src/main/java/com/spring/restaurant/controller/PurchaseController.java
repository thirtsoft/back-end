package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.PurchaseApi;
import com.spring.restaurant.dto.PurchaseRequest;
import com.spring.restaurant.dto.PurchaseResponse;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.service.PurchaseService;
import com.spring.restaurant.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080
@CrossOrigin("http://localhost:4200")
@RestController
//@RequestMapping("/api/buy")
// http://localhost:8080/api/buy
public class PurchaseController implements PurchaseApi {

    private final PurchaseService purchaseService;

    private final UtilisateurService utilisateurService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, UtilisateurService utilisateurService) {
        this.purchaseService = purchaseService;
        this.utilisateurService = utilisateurService;
    }

    // http://localhost:8080/api/buy/purchase
    /*
    @PostMapping("/purchase")
    public PurchaseResponse addRequestOrder(@RequestBody PurchaseRequest purchaseRequest) {
        System.out.println(purchaseRequest.getItems().size());

        return purchaseService.addRequestOrder(purchaseRequest);
    }
    */

    //  @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> addRequestOrder(@RequestBody PurchaseRequest purchaseRequest, @RequestParam Long id) {
        System.out.println(purchaseRequest.getItems().size());

        Utilisateur utilisateur = utilisateurService.findUtilisateurById(id).get();
        purchaseRequest.setUtilisateur(utilisateur);

        PurchaseResponse response = this.purchaseService.addRequestOrder(purchaseRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}
