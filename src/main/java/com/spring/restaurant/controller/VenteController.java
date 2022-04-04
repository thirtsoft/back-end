package com.spring.restaurant.controller;

import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.model.Vente;
import com.spring.restaurant.security.services.UserPrinciple;
import com.spring.restaurant.service.UtilisateurService;
import com.spring.restaurant.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class VenteController {

    @Autowired
    private VenteService venteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vente> getAllVentes() {
        return venteService.findAllVentes();
    }

    @GetMapping(value = APP_ROOT + "/ventes/allVenteOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Vente>> getAllVenteOrderDesc() {
        List<Vente> venteList = venteService.findAllVentesOrderDesc();
        return new ResponseEntity<>(venteList, HttpStatus.OK);
    }

    @GetMapping(value = APP_ROOT + "/ventes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> getVenteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Vente vente = venteService.findVenteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente Not found"));
        return ResponseEntity.ok().body(vente);

    }

    @GetMapping(value = APP_ROOT + "/ventes/searchVenteByNumeroVente", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vente getVenteByNumeroVente(@RequestParam("num") Long numeroVente) {
        return venteService.findVenteByNumeroVente(numeroVente);
    }

    @GetMapping(value = APP_ROOT + "/ventes/SumsOfVentesByYear")
    public BigDecimal sumTotalOfVentesByYear() {
        return venteService.sumTotalOfVentesByYear();
    }

    @GetMapping(value = APP_ROOT + "/ventes/generateNumeroVente")
    public long generateNumeroVente() {
        return venteService.generateNumeroVente();
    }

    @PostMapping(value = APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> createVente(@RequestBody Vente vente, @RequestParam Long id) {

        Utilisateur userInfo = utilisateurService.findUtilisateurById(id).get();

        Vente venteResultat;

        vente.setUtilisateur(userInfo);
        vente.setNumeroVente(this.generateNumeroVente());

        venteResultat = venteService.saveVente(vente);

        return new ResponseEntity<>(venteResultat, HttpStatus.CREATED);
    }

    @PostMapping(value = APP_ROOT + "/ventes/venteWithbarCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> saveVenteWithBarcode(@RequestBody Vente vente, @RequestParam Long id) {

        Vente venteResultat;

        Utilisateur userInfo = utilisateurService.findUtilisateurById(id).get();

        vente.setUtilisateur(userInfo);
        vente.setNumeroVente(this.generateNumeroVente());

        venteResultat = venteService.saveVenteWithBarcode(vente);


        return new ResponseEntity<>(venteResultat, HttpStatus.CREATED);
    }

    @PutMapping(value = APP_ROOT + "/ventes/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> updateVente(@PathVariable(value = "id") Long id, @RequestBody Vente vente) throws Exception {

        Vente ventedResult;

        vente.setId(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisation = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisation.get();

        ventedResult = venteService.saveVente(vente);

        return new ResponseEntity<>(ventedResult, HttpStatus.OK);

    }

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{id}")
    public ResponseEntity<?> deleteVente(@PathVariable(value = "id") Long id) {

        Optional<Vente> optionalVent = venteService.findVenteById(id);
        Vente ventedResult = optionalVent.get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisation = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisation.get();

        venteService.deleteVente(id);

        return ResponseEntity.ok().build();
    }


    @GetMapping(value = APP_ROOT + "/ventes/searchSumVenteByMonth")
    public List<?> getSumTotalOfVenteByMonth() {
        return venteService.sumTotalOfVenteByMonth();
    }

    @GetMapping(value = APP_ROOT + "/ventes/searchSumVenteByYears")
    public List<?> getSumTotalOfVenteByYears() {
        return venteService.sumTotalOfVenteByYears();
    }

    @GetMapping(value = APP_ROOT + "/ventes/searchSumsOfVenteByDay")
    public BigDecimal getSumsOfVenteByDay() {
        return venteService.sumTotalOfVenteByDay();
    }

    @GetMapping(value = APP_ROOT + "/ventes/searchListVenteByEmpId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vente> findListVenteByEmployeId(Long empId) {
        return venteService.findListVenteByEmployeId(empId);
    }
}
