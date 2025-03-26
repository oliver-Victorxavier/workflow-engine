package org.example.workflow.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a workflow definition with states and transitions between them.
 * A workflow is essentially a state machine that defines the possible states
 * and the valid transitions between those states.
 *
 * @author Oliver-Victorxavier
 * @since 1.0
 */
public class Workflow {
    private String name;
    private Map<String, State> states;
    private String initialState;

    /**
     * Creates a new workflow with the specified name.
     *
     * @param name A unique identifier for this workflow
     */
    public Workflow(String name) {
        this.name = name;
        this.states = new HashMap<>();
    }
    /**
     * Adds a state to this workflow.
     * The first state added becomes the initial state automatically.
     *
     * @param state The state to add to this workflow
     * @return This workflow instance for method chaining
     */
    public Workflow addState(State state) {
        states.put(state.getName(), state);

        // The first state added becomes the initial state
        if (initialState == null) {
            initialState = state.getName();
        }

        return this;
    }
    /**
     * Retrieves a state by its name.
     *
     * @param stateName The name of the state to retrieve
     * @return The state with the specified name, or null if not found
     */
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