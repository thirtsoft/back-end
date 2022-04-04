package com.spring.restaurant.service;

import com.spring.restaurant.deo.RequestOrderRepository;
import com.spring.restaurant.model.RequestOrder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RequestOrderService {

    private final RequestOrderRepository requestOrderRepository;
    private final OrderService orderService;

    double total = 0;
    Logger logger = LoggerFactory.getLogger(RequestOrderService.class);

    @Autowired
    public RequestOrderService(RequestOrderRepository requestOrderRepository,
                               OrderService orderService) {
        this.requestOrderRepository = requestOrderRepository;
        this.orderService = orderService;
    }

    public RequestOrder save(RequestOrder commandeDto) {
        logger.info("CommandeDto {}", commandeDto);


        RequestOrder savedCmdClt = requestOrderRepository.save(commandeDto);

        savedCmdClt.setTotalPrice(total);
        savedCmdClt.setDateCommande(new Date());

        return requestOrderRepository.save(savedCmdClt);


    }

    public RequestOrder findById(Long id) {
        if (id == null) {
            log.error("RequestOrder Id is null");
            return null;
        }

        Optional<RequestOrder> commande = requestOrderRepository.findById(id);

        return commande.get();

    }

    public List<RequestOrder> findAll() {
        return requestOrderRepository.findAll();
    }

    public List<RequestOrder> findByOrderByIdDesc() {
        return requestOrderRepository.findByOrderByIdDesc();
    }

    public BigDecimal sumTotalOfCommandeBInDay() {
        return requestOrderRepository.sumTotalOfCommandeInDay();
    }

    public BigDecimal sumTotaleOfCommandeInMonth() {
        return requestOrderRepository.sumTotalOfCommandesInMonth();
    }

    public BigDecimal sumTotaleOfCommandeInYear() {
        return requestOrderRepository.sumTotalOfCommandesInYearYear();
    }

    public List<?> sumTotalOfCommandePeerMonth() {
        return requestOrderRepository.sumTotalOfCommandePeerMonth();
    }

    public List<?> sumTotalOfOrdersPeerYears() {
        return requestOrderRepository.sumTotalOfCommandePeerYears();
    }

    public void delete(Long id) {
        if (id == null) {
            log.error("Commande Id is null");
            return;
        }
        requestOrderRepository.deleteById(id);

    }

}
