package com.spring.restaurant.controller.api;

import com.spring.restaurant.dto.PurchaseRequest;
import com.spring.restaurant.dto.PurchaseResponse;
import com.spring.restaurant.model.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface PurchaseApi {

    @PostMapping(value = APP_ROOT + "/purchase")
    ResponseEntity<PurchaseResponse> addRequestOrder(@RequestBody PurchaseRequest purchaseRequest, @RequestParam Long id);


}
