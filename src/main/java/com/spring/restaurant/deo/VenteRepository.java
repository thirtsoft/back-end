package com.spring.restaurant.deo;

import com.spring.restaurant.model.LigneVente;
import com.spring.restaurant.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {

    @Query("select v from Vente v where (v.dateVente <= current_date - 90) ORDER BY v.dateVente DESC ")
    List<Vente> findListVenteOf3LatestMonth();

    List<Vente> findTop500ByOrderByIdDesc();

    @Query("select sum(v.totalVente) from Vente v where v.dateVente > current_date")
    BigDecimal sumTotalOfVenteByDay();

    @Query("select sum(v.totalVente) from Vente v where month(v.dateVente) = month(current_date)")
    BigDecimal sumTotalOfVentesByMonth();

    @Query("select EXTRACT(month from(v.dateVente)), sum(v.totalVente) from Vente v group by EXTRACT(month from(v.dateVente))")
    List<?> sumTotalOfVenteByMonth();

    @Query("select EXTRACT(year from(v.dateVente)), sum(v.totalVente) from Vente v group by EXTRACT(year from(v.dateVente))")
    List<?> sumTotalOfVenteByYears();

    @Query("select p from Vente p where p.numeroVente like :num")
    Vente findByNumeroVente(@Param("num") Long numeroVente);

    @Query("select c from Vente c where c.status like :status")
    List<Vente> ListCommandeClientByStatus(@Param("status") String status);

    @Query("select v from Vente v where v.utilisateur.id =:emp")
    List<Vente> findAllVenteByEmployeId(@Param("emp") Long empId);

    List<Vente> findByOrderByIdDesc();
}
