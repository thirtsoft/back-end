package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.LigneVenteApi;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.LigneVente;
import com.spring.restaurant.service.LigneVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LigneVenteController implements LigneVenteApi {

    @Autowired
    private LigneVenteService ligneVenteService;

    //   @GetMapping(value = APP_ROOT + "/ligneVentes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVente>> getAllLigneVentes() {
        List<LigneVente> ligneVenteList = ligneVenteService.findAllLigneVentes();
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    //  @GetMapping(value = APP_ROOT + "/ligneVentes/allLigneVenteOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVente>> getAllLigneVenteOrderDesc() {
        List<LigneVente> ligneVenteList = ligneVenteService.findAllLigneVentesOrderDesc();
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    //  @GetMapping(value = APP_ROOT + "/ligneVentes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LigneVente> getLigneVenteById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        LigneVente ligneVente = ligneVenteService.findLigneVenteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne Vente Not found"));
        return ResponseEntity.ok().body(ligneVente);

    }

    //  @GetMapping(value = APP_ROOT + "/ligneVentes/findByNumero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVente>> getAllByNumero(@PathVariable(value = "id") Long numero) {
        System.out.println("Get all Lventes...");
        List<LigneVente> Lventes = new ArrayList<>();
        ligneVenteService.findAllLventeByNumero(numero).forEach(Lventes::add);
        return new ResponseEntity<>(Lventes, HttpStatus.OK);
    }

    //  @GetMapping(value = APP_ROOT + "/ligneVentes/searchListLigneVentestByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVente>> getAllLigneVenteByProduitId(@RequestParam("prodId") Long prodId) {
        List<LigneVente> ligneVenteList = ligneVenteService.findLigneVenteByProduitId(prodId);
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    //  @GetMapping(value = APP_ROOT + "/ligneVentes/searchListLigneVentesByVenteId/{venteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVente>> getAllLigneVenteByVenteId(@PathVariable("venteId") Long venteId) {
        List<LigneVente> ligneVenteList = ligneVenteService.findLigneVenteByVenteId(venteId);
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    //  @PostMapping(value = APP_ROOT + "/ligneVentes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LigneVente> createLigneVente(@RequestBody LigneVente ligneVente) {
        return new ResponseEntity<>(ligneVenteService.saveLigneVente(ligneVente), HttpStatus.CREATED);
    }

    //  @PutMapping(value = APP_ROOT + "/ligneVentes/update/{lcId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LigneVente> updateLigneVente(@PathVariable(value = "lventeId") Long lventeId, @RequestBody LigneVente ligneVente) {
        ligneVente.setId(lventeId);
        return new ResponseEntity<>(ligneVenteService.saveLigneVente(ligneVente), HttpStatus.OK);

    }

    // @DeleteMapping(value = APP_ROOT + "/ligneVentes/delete/{id}")
    public ResponseEntity<?> deleteLigneVente(@PathVariable(value = "id") Long id) {
        ligneVenteService.deleteLigneVente(id);
        return ResponseEntity.ok().build();

    }

}
