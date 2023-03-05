package com.spring.restaurant.deo;

import com.spring.restaurant.model.Client;
import com.spring.restaurant.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Country> findByOrderByIdDesc();
}