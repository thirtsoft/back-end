package com.spring.restaurant.deo;

import com.spring.restaurant.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByOrderByIdDesc();

    @Query("select p from Item p where p.requestOrder.id =:num")
    List<Item> ListItemByRequestOrderId(@Param("num") Long comId);
}
