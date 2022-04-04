package com.spring.restaurant.service;

import com.spring.restaurant.deo.ClientRepository;
import com.spring.restaurant.deo.RequestOrderRepository;
import com.spring.restaurant.dto.PurchaseRequest;
import com.spring.restaurant.dto.PurchaseResponse;
import com.spring.restaurant.model.Item;
import com.spring.restaurant.model.RequestOrder;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.util.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final ClientRepository clientRepository;

    private final RequestOrderRepository requestOrderRepository;

    @Autowired
    public PurchaseServiceImpl(ClientRepository clientRepository, RequestOrderRepository requestOrderRepository) {
        this.clientRepository = clientRepository;
        this.requestOrderRepository = requestOrderRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse addRequestOrder(PurchaseRequest purchases) {
        /* #1 */
        RequestOrder requestOrder = purchases.getRequestOrder();
        /* #2 */
        Long numCommande = this.generateNumeroCommande();
        String myCode = UserCode.getCode();
        requestOrder.setCode(myCode);
        requestOrder.setDateCommande(new Date());
        requestOrder.setNumeroCommande(numCommande);

        requestOrder.setStatus("VALIDEE");

        //    User user = purchases.getUser();
        Utilisateur utilisateur = purchases.getUtilisateur();

        requestOrder.setUtilisateur(utilisateur);

        /* #3 */
        List<Item> items = purchases.getItems();
        items.forEach(item -> requestOrder.addItem(item));


        /* #5 */

        //    purchases.getClient().addRequestOrder(requestOrder);

        //    clientRepository.save(purchases.getClient());

        requestOrderRepository.save(requestOrder);

        return new PurchaseResponse(myCode);
    }

    public long generateNumeroCommande() {
        final String FORMAT = "yyyyMMddHHmmss";
        return Long.parseLong(DateTimeFormat.forPattern(FORMAT).print(LocalDateTime.now()));
    }


}
