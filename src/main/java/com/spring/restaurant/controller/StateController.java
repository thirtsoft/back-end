package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.StateApi;
import com.spring.restaurant.model.State;
import com.spring.restaurant.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// http://localhost:8080/api
@RestController
@CrossOrigin("http://localhost:4200")
//@RequestMapping("/api")
public class StateController implements StateApi {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    public ResponseEntity<State> createState(State state) {
        State stateCreated = stateService.saveState(state);
        return new ResponseEntity<>(stateCreated, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<State> updateState(Long catId, State state) {
        state.setId(catId);
        State stateUpdate = stateService.saveState(state);
        return new ResponseEntity<>(stateUpdate, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<State> getStateById(Long catId) {
        State stateSearch = stateService.findStateById(catId);
        return new ResponseEntity<>(stateSearch, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<State>> getAllState() {
        List<State> stateList = stateService.findAllStates();
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<State>> getAllStatesOrderDesc() {
        List<State> stateList = stateService.findAllStatesOrderDesc();
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    // http://localhost:8080/api/statescode?code={value}
    //   @GetMapping("/statescode")
    public ResponseEntity<List<State>> getStatesByCode(String code) {
        List<State> stateList = stateService.findAllStatesByCountryCode(code);
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    @Override
    public void deleteStateById(Long catId) {
        stateService.deleteState(catId);
    }
}
