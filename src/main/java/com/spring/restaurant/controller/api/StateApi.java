package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.State;
import com.spring.restaurant.model.State;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface StateApi {

    @PostMapping(value = APP_ROOT + "/states/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<State> createState(@RequestBody State state);

    @PutMapping(value = APP_ROOT + "/states/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<State> updateState(@PathVariable("catId") Long catId, @RequestBody State state);

    @GetMapping(value = APP_ROOT + "/states/findById/{catId}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<State> getStateById(@PathVariable("catId") Long catId);

    @GetMapping(value = APP_ROOT + "/states/allStates")
    ResponseEntity<List<State>>  getAllState();

    @GetMapping(value = APP_ROOT + "/states/searchAllStatesOrderByIdDesc")
    ResponseEntity<List<State>> getAllStatesOrderDesc();

    @GetMapping(value = APP_ROOT + "/states/statescode")
    ResponseEntity<List<State>> getStatesByCode(@RequestParam String code);

    @DeleteMapping(value = APP_ROOT + "/states/delete/{catId}")
    void deleteStateById(@PathVariable("catId") Long catId);


}
