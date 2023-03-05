package com.spring.restaurant.service;

import com.spring.restaurant.model.RequestOrder;

import java.math.BigDecimal;
import java.util.List;


public interface RequestOrderService {

    RequestOrder save(RequestOrder commandeDto);

    RequestOrder findById(Long id);

    List<RequestOrder> findAll();

    List<RequestOrder> findByOrderByIdDesc();

    BigDecimal sumTotalOfCommandeBInDay();

    BigDecimal sumTotaleOfCommandeInMonth();

    BigDecimal sumTotaleOfCommandeInYear();

    List<?> sumTotalOfCommandePeerMonth();

    List<?> sumTotalOfOrdersPeerYears();

    void delete(Long id);

}
