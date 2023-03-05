package com.spring.restaurant.service.Impl;

import com.spring.restaurant.deo.StateRepository;
import com.spring.restaurant.model.Country;
import com.spring.restaurant.model.State;
import com.spring.restaurant.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State saveState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State updateState(Long catId, State state) {
        if (!stateRepository.existsById(catId)) {
            log.error("State not found");
        }

        State updateState= stateRepository.findById(catId).get();
        updateState.setName(state.getName());
        updateState.setDataCreate(state.getDataCreate());

        return stateRepository.save(updateState);
    }

    @Override
    public State findStateById(Long id) {
        return stateRepository.findById(id).get();
    }

    @Override
    public List<State> findAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public List<State> findAllStatesOrderDesc() {
        return stateRepository.findByOrderByIdDesc();
    }

    @Override
    public List<State> findAllStatesByCountryCode(String code) {
        return stateRepository.findByCountryCode(code);
    }

    @Override
    public void deleteState(Long catId) {
        if (catId == null) {
            return;
        }
        stateRepository.deleteById(catId);
    }
}
