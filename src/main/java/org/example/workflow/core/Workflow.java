package org.example.workflow.core;

import java.util.HashMap;
import java.util.Map;

public class Workflow {
    private String name;
    private Map<String, State> states;
    private String initialState;

    public Workflow(String name) {
        this.name = name;
        this.states = new HashMap<>();
    }

    public Workflow addState(State state) {
        states.put(state.getName(), state);

        // The first state added becomes the initial state
        if (initialState == null) {
            initialState = state.getName();
        }

        return this;
    }

    public State getState(String stateName) {
        return states.get(stateName);
    }

    public String getName() {
        return name;
    }

    public String getInitialState() {
        return initialState;
    }

    public Map<String, State> getStates() {
        return states;
    }
}