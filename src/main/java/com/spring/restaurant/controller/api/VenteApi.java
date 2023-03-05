package com.spring.restaurant.controller.api;

import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.model.Vente;
import com.spring.restaurant.security.services.UserPrinciple;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface VenteApi {

    @GetMapping(value = APP_ROOT + "/requestOrders/all")
    List<Vente> getAllrequestOrders();

    @GetMapping(value = APP_ROOT + "/requestOrders/allVenteOrderDesc")
    ResponseEntity<List<Vente>> getAllVenteOrderDesc();

    @GetMapping(value = APP_ROOT + "/requestOrders/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vente> getVenteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/requestOrders/searchVenteByNumeroVente", produces = MediaType.APPLICATION_JSON_VALUE)
    Vente getVenteByNumeroVente(@RequestParam("num") Long numeroVente);

    @GetMapping(value = APP_ROOT + "/requestOrders/SumsOfrequestOrdersByYear")
    BigDecimal sumTotalOfrequestOrdersByYear();

    @GetMapping(value = APP_ROOT + "/requestOrders/generateNumeroVente")
    long generateNumeroVente();

    @PostMapping(value = APP_ROOT + "/requestOrders/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vente> createVente(@RequestBody Vente vente, @RequestParam Long id);

    @PostMapping(value = APP_ROOT + "/requestOrders/venteWithbarCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vente> saveVenteWithBarcode(@RequestBody Vente vente, @RequestParam Long id);

    @PutMapping(value = APP_ROOT + "/requestOrders/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vente> updateVente(@PathVariable(value = "id") Long id, @RequestBody Vente vente) throws Exception;

    @GetMapping(value = APP_ROOT + "/requestOrders/searchSumVenteByMonth")
    List<?> getSumTotalOfVenteByMonth();

    @GetMapping(value = APP_ROOT + "/requestOrders/searchSumVenteByYears")
    List<?> getSumTotalOfVenteByYears();

    @GetMapping(value = APP_ROOT + "/requestOrders/searchSumsOfVenteByDay")
    BigDecimal getSumsOfVenteByDay();

    @GetMapping(value = APP_ROOT + "/requestOrders/searchListVenteByEmpId", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Vente> findListVenteByEmployeId(Long empId);

    @DeleteMapping(value = APP_ROOT + "/requestOrders/delete/{id}")
    ResponseEntity<?> deleteVente(@PathVariable(value = "id") Long id);
}
