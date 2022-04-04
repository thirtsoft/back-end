package com.spring.restaurant.service.Impl;

import com.spring.restaurant.deo.VenteRepository;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.LigneVente;
import com.spring.restaurant.model.Order;
import com.spring.restaurant.model.Vente;
import com.spring.restaurant.service.LigneVenteService;
import com.spring.restaurant.service.OrderService;
import com.spring.restaurant.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private LigneVenteService ligneVenteService;

    @Autowired
    private OrderService produitService;

    @Override
    public List<Vente> findAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public List<Vente> findAllVentesOrderDesc() {
        return venteRepository.findByOrderByIdDesc();
    }

    @Override
    public Optional<Vente> findVenteById(Long venteId) {
        if (!venteRepository.existsById(venteId)) {
            throw new ResourceNotFoundException("Vente Not found");
        }

        return venteRepository.findById(venteId);
    }

    @Override
    public Vente saveVente(Vente vente) {
        System.out.println("Initial Numero Vente " + vente.getNumeroVente());

        List<LigneVente> ligneVente = vente.getLigneVentes();

        if (ligneVente == null || ligneVente.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins ajouter un produit");
        }

        for (LigneVente ligneV : ligneVente) {
            Order produitInitial = produitService.getOrder(ligneV.getOrder().getId());
            if (ligneV.getQuantite() > produitInitial.getQuantity()) {
                throw new IllegalArgumentException("La Quantité de stock du produit est insuffusante");
            }
        }

        venteRepository.save(vente);

        System.out.println("Milieu Numero Vente " + vente.getNumeroVente());

        List<LigneVente> ligneVentes = vente.getLigneVentes();

        double total = 0;

        for (LigneVente lvente : ligneVentes) {
            lvente.setVente(vente);
            lvente.setNumero(vente.getNumeroVente());

            ligneVenteService.saveLigneVente(lvente);

            Order produit = produitService.getOrder(lvente.getOrder().getId());

            if (produit != null) {
                produit.setQuantity(produit.getQteStock() - lvente.getQuantite());
                produitService.updateOrder(produit.getId(), produit);
            }

            lvente.setPrixVente(produit.getPrice());

            System.out.println(produit.getPrice());
            System.out.println(lvente.getQuantite());
            System.out.println(lvente.getQuantite() * produit.getPrice());

            total += (lvente.getQuantite() * produit.getPrice());

        }

        vente.setTotalVente(total);
        vente.setStatus("VALIDEE");
        vente.setDateVente(new Date());
        vente.setUtilisateur(vente.getUtilisateur());

        System.out.println("Fin Numero Vente " + vente.getNumeroVente());

        return venteRepository.save(vente);

    }

    @Override
    public Vente saveVenteWithBarcode(Vente vente) {

        System.out.println("Initial Vente " + vente);

        System.out.println("Initial Numero Vente " + vente.getNumeroVente());

        List<LigneVente> ligneVente = vente.getLigneVentes();

        if (ligneVente == null || ligneVente.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins ajouter un produit");
        }


        venteRepository.save(vente);

        System.out.println("Milieu Numero Vente " + vente.getNumeroVente());

        List<LigneVente> ligneVentes = vente.getLigneVentes();

        double total = 0;

        for (LigneVente lvente : ligneVentes) {
            lvente.setVente(vente);
            lvente.setNumero(vente.getNumeroVente());

            ligneVenteService.saveLigneVente(lvente);

            Order produit = produitService.getOrder(lvente.getOrder().getId());

            if (produit != null) {
                produit.setQteStock(produit.getQteStock() - lvente.getQuantite());
                produitService.updateOrder(produit.getId(), produit);
            }

            lvente.setPrixVente(produit.getPrice());

            System.out.println(produit.getPrice());
            System.out.println(lvente.getQuantite());
            System.out.println(lvente.getQuantite() * produit.getPrice());

            total += (lvente.getQuantite() * produit.getPrice());

        }

        vente.setTotalVente(total);
        vente.setStatus("VALIDEE");
        vente.setDateVente(new Date());
        vente.setUtilisateur(vente.getUtilisateur());

        System.out.println("Fin Numero Vente " + vente.getNumeroVente());

        return venteRepository.save(vente);

    }

    @Override
    public Vente updateVente(Long venteId, Vente vente) {
        if (!venteRepository.existsById(venteId)) {
            throw new ResourceNotFoundException("Vente N° " + venteId + "not found");
        }
        Optional<Vente> venteProd = venteRepository.findById(venteId);
        if (!venteProd.isPresent()) {
            throw new ResourceNotFoundException("Vente N ° " + venteId + "not found");
        }
        Vente venteResultat = venteProd.get();

        venteResultat.setNumeroVente(vente.getNumeroVente());
        venteResultat.setTotalVente(vente.getTotalVente());
        venteResultat.setDateVente(new Date());

        return venteRepository.save(venteResultat);
    }


    @Override
    public long generateNumeroVente() {
        final String FORMAT = "yyyyMMddHHmmss";
        return Long.parseLong(DateTimeFormat.forPattern(FORMAT).print(LocalDateTime.now()));
    }


    @Override
    public Vente findVenteByNumeroVente(Long numeroVente) {
        return venteRepository.findByNumeroVente(numeroVente);
    }

    @Override
    public ResponseEntity<Object> deleteVenteClient(Long id) {
        if (!venteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vente Not found");
        }
        venteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteVente(Long id) {
        Optional<Vente> venteInfo = venteRepository.findById(id);
        if (venteInfo.isPresent()) {
            Vente vente = venteInfo.get();
            ligneVenteService.deleteLventeByNumero(vente.getNumeroVente());
            venteRepository.delete(vente);
        }
    }


    @Override
    public BigDecimal sumTotalOfVenteByDay() {
        return venteRepository.sumTotalOfVenteByDay();
    }

    @Override
    public BigDecimal sumTotalOfVentesByYear() {
        return null;
    }

    @Override
    public List<?> sumTotalOfVenteByMonth() {
        return venteRepository.sumTotalOfVenteByMonth();
    }

    @Override
    public List<?> sumTotalOfVenteByYears() {
        return venteRepository.sumTotalOfVenteByYears();
    }

    @Override
    public List<Vente> findListVenteByEmployeId(Long empId) {
        return venteRepository.findAllVenteByEmployeId(empId);
    }


}
