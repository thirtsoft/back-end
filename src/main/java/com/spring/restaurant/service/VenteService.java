package com.spring.restaurant.service;

import com.spring.restaurant.model.Vente;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VenteService {

    List<Vente> findAllVentes();

    List<Vente> findAllVentesOrderDesc();

    Optional<Vente> findVenteById(Long venteId);

    Vente saveVente(Vente vente);

    Vente saveVenteWithBarcode(Vente vente);

    Vente updateVente(Long venteId, Vente vente);

    ResponseEntity<Object> deleteVenteClient(Long id);

    long generateNumeroVente();

    Vente findVenteByNumeroVente(Long numeroVente);

    void deleteVente(Long id);

    BigDecimal sumTotalOfVenteByDay();

    BigDecimal sumTotalOfVentesByYear();

    List<?> sumTotalOfVenteByMonth();

    List<?> sumTotalOfVenteByYears();

    List<Vente> findListVenteByEmployeId(Long empId);
}
