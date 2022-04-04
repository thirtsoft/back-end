package com.spring.restaurant.controller;


import com.spring.restaurant.model.RequestOrder;
import com.spring.restaurant.service.RequestOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class RequestOrderController {

    private final RequestOrderService requestOrderService;

    @Autowired
    public RequestOrderController(RequestOrderService requestOrderService) {
        this.requestOrderService = requestOrderService;
    }

    @PostMapping("/ventes/create")
    public ResponseEntity<RequestOrder> save(@RequestBody RequestOrder commandeDto) {
        RequestOrder requestOrder = requestOrderService.save(commandeDto);
        return new ResponseEntity<>(requestOrder, HttpStatus.CREATED);
    }

    @GetMapping("/ventes/findById/{id}")
    public ResponseEntity<RequestOrder> findById(@PathVariable("id") Long id) {
        RequestOrder requestOrder = requestOrderService.findById(id);
        return new ResponseEntity<>(requestOrder, HttpStatus.OK);
    }

    @GetMapping("/ventes/sumVenteInDay")
    public BigDecimal sumTotaleOfCommandeInDay() {
        return requestOrderService.sumTotalOfCommandeBInDay();
    }

    @GetMapping("/ventes/sumVenteInMonth")
    public BigDecimal sumTotaleOfCommandeInMonth() {
        return requestOrderService.sumTotaleOfCommandeInMonth();
    }

    @GetMapping("/ventes/sumVenteInYear")
    public BigDecimal sumTotaleOfCommandeInYear() {
        return requestOrderService.sumTotaleOfCommandeInYear();
    }

    @GetMapping("/ventes/all")
    public ResponseEntity<List<RequestOrder>> findAll() {
        List<RequestOrder> commandeDtoList = requestOrderService.findAll();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @GetMapping("/ventes/searchAllVentesOrderByIdDesc")
    public ResponseEntity<List<RequestOrder>> getAllCommandesOrderByIdDesc() {
        List<RequestOrder> commandeDtoList = requestOrderService.findByOrderByIdDesc();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @GetMapping("/ventes/sumTotalOfVentesPeerMonth")
    public List<?> getSumTotaleOfCommandePeerMonth() {
        return requestOrderService.sumTotalOfCommandePeerMonth();
    }

    @GetMapping("/ventes/sumTotalOfVentesPeerYear")
    public List<?> getSumTotalOfOrdersPeerYears() {
        return requestOrderService.sumTotalOfOrdersPeerYears();
    }

    @GetMapping("/ventes/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        requestOrderService.delete(id);
    }
}
