package com.spring.restaurant.controller.api;

import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.LigneVente;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface LigneVenteApi {

    @PostMapping(value = APP_ROOT + "/ligneVentes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneVente> createLigneVente(@RequestBody LigneVente ligneVente);

    @PutMapping(value = APP_ROOT + "/ligneVentes/update/{lcId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneVente> updateLigneVente(@PathVariable(value = "lventeId") Long lventeId, @RequestBody LigneVente ligneVente);

    @GetMapping(value = APP_ROOT + "/ligneVentes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneVente> getLigneVenteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneVentes/all")
    ResponseEntity<List<LigneVente>> getAllLigneVentes();

    @GetMapping(value = APP_ROOT + "/ligneVentes/allLigneVenteOrderDesc")
    ResponseEntity<List<LigneVente>> getAllLigneVenteOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneVentes/searchListLigneVentestByProduitId")
    ResponseEntity<List<LigneVente>> getAllLigneVenteByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneVentes/findByNumero/{id}")
    ResponseEntity<List<LigneVente>> getAllByNumero(@PathVariable(value = "id") Long numero);

    @GetMapping(value = APP_ROOT + "/ligneVentes/searchListLigneVentesByVenteId/{venteId}")
    ResponseEntity<List<LigneVente>> getAllLigneVenteByVenteId(@PathVariable("venteId") Long venteId);

    @DeleteMapping(value = APP_ROOT + "/ligneVentes/delete/{id}")
    ResponseEntity<?> deleteLigneVente(@PathVariable(value = "id") Long id);

}
