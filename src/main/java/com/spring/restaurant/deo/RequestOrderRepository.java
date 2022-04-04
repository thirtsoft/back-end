package com.spring.restaurant.deo;

import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.RequestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RequestOrderRepository extends JpaRepository<RequestOrder,Long> {

    @Query("select sum(c.totalPrice) from RequestOrder c where c.dataCreate > current_date")
    BigDecimal sumTotalOfCommandeInDay();

    @Query("select sum(c.totalPrice) from RequestOrder c where month(c.dataCreate) = month(current_date)")
    BigDecimal sumTotalOfCommandesInMonth();

    @Query("select sum(c.totalPrice) from RequestOrder c where year(c.dataCreate) = year(current_date)")
    BigDecimal sumTotalOfCommandesInYearYear();

    List<RequestOrder> findByOrderByIdDesc();

    @Query("select EXTRACT(month from(c.dataCreate)), sum(c.totalPrice) from RequestOrder c group by EXTRACT(month from(c.dataCreate))")
    List<?> sumTotalOfCommandePeerMonth();

    @Query("select EXTRACT(year from(v.dataCreate)), sum(v.totalPrice) from RequestOrder v group by EXTRACT(year from(v.dataCreate))")
    List<?> sumTotalOfCommandePeerYears();

}
