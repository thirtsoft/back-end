package com.spring.restaurant.service.Impl;

import com.spring.restaurant.deo.LigneVenteRepository;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.LigneVente;
import com.spring.restaurant.service.LigneVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneVenteServiceImpl implements LigneVenteService {

    @Autowired
    private LigneVenteRepository ligneVenteRepository;

    @Override
    public List<LigneVente> findAllLigneVentes() {
        return ligneVenteRepository.findAll();
    }

    @Override
    public List<LigneVente> findAllLigneVentesOrderDesc() {
        return ligneVenteRepository.findByOrderByIdDesc();
    }

    @Override
    public Optional<LigneVente> findLigneVenteById(Long lventeId) {
        if (!ligneVenteRepository.existsById(lventeId)) {
            throw new ResourceNotFoundException("Ligne Vente Not found");
        }
        return ligneVenteRepository.findById(lventeId);
    }

    @Override
    public Optional<LigneVente> findLigneVenteByCode(String code) {
        if (ligneVenteRepository.findByCode(code) == null) {
            throw new ResourceNotFoundException("Ligne Vente Not found");
        }
        return ligneVenteRepository.findByCode(code);
    }

    @Override
    public LigneVente saveLigneVente(LigneVente ligneVente) {
        return ligneVenteRepository.save(ligneVente);
    }

    @Override
    public LigneVente updateLigneVente(Long lventeId, LigneVente ligneVente) {
        if (!ligneVenteRepository.existsById(lventeId)) {
            throw new ResourceNotFoundException("Ligne Vente Not found");
        }
        Optional<LigneVente> lignevente = ligneVenteRepository.findById(lventeId);
        if (!lignevente.isPresent()) {
            throw new ResourceNotFoundException("Ligne Vente Not found");
        }

        LigneVente ligneVenteResultat = lignevente.get();

        ligneVenteResultat.setNumero(ligneVente.getNumero());
        ligneVenteResultat.setPrixVente(ligneVente.getOrder().getPrice());
        ligneVenteResultat.setQuantite(ligneVente.getQuantite());
        ligneVenteResultat.setOrder(ligneVente.getOrder());
        ligneVenteResultat.setVente(ligneVente.getVente());

        return ligneVenteRepository.save(ligneVenteResultat);
    }

    @Override
    public ResponseEntity<Object> deleteLigneVente(Long ligneVenteId) {
        if (!ligneVenteRepository.existsById(ligneVenteId)) {
            throw new ResourceNotFoundException("Linge Vente Not found");
        }
        ligneVenteRepository.deleteById(ligneVenteId);

        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteLventeByNumero(Long numero) {
        ligneVenteRepository.deleteByNumero(numero);
    }

    @Override
    public List<LigneVente> findAllLventeByNumero(Long numero) {
        return ligneVenteRepository.findAllByNumero(numero);
    }

    @Override
    public List<LigneVente> findAllLventeByCodeCode(String code) {
        return ligneVenteRepository.findAllByCode(code);
    }

    @Override
    public List<LigneVente> findLigneVenteByProduitId(Long prodId) {
        return null;
    }

    @Override
    public List<LigneVente> findLigneVenteByVenteId(Long venteId) {
        return ligneVenteRepository.ListLigneVenteByVenteId(venteId);
    }

}
