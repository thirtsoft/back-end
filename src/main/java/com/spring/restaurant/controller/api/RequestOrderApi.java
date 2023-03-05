package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.RequestOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface RequestOrderApi {

    @PostMapping(value = APP_ROOT + "/ventes/create")
    ResponseEntity<RequestOrder> save(@RequestBody RequestOrder commandeDto);

    @GetMapping(value = APP_ROOT + "/ventes/findById/{id}")
    ResponseEntity<RequestOrder> findById(@PathVariable("id") Long id);

    @GetMapping(value = APP_ROOT + "/ventes/sumVenteInDay")
    BigDecimal sumTotaleOfCommandeInDay();

    @GetMapping(value = APP_ROOT + "/ventes/sumVenteInMonth")
    BigDecimal sumTotaleOfCommandeInMonth();

    @GetMapping(value = APP_ROOT + "/ventes/sumVenteInYear")
    BigDecimal sumTotaleOfCommandeInYear();

    @GetMapping(value = APP_ROOT + "/ventes/all")
    ResponseEntity<List<RequestOrder>> findAll();

    @GetMapping(value = APP_ROOT + "/ventes/searchAllVentesOrderByIdDesc")
    ResponseEntity<List<RequestOrder>> getAllCommandesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/ventes/sumTotalOfVentesPeerMonth")
    List<?> getSumTotaleOfCommandePeerMonth();

    @GetMapping(value = APP_ROOT + "/ventes/sumTotalOfVentesPeerYear")
    List<?> getSumTotalOfOrdersPeerYears();

    @GetMapping(value = APP_ROOT + "/ventes/delete/{id}")
    void delete(@PathVariable("id") Long id);

}
