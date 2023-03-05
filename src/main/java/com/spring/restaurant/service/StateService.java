package com.spring.restaurant.service;

import com.spring.restaurant.model.State;

import java.util.List;

public interface StateService {

    State saveState(State state);

    State updateState(Long catId, State state);

    State findStateById(Long id);

    List<State> findAllStates();

    List<State> findAllStatesOrderDesc();

    List<State> findAllStatesByCountryCode(String code);

    void deleteState(Long catId);
}
