package org.example.workflow.core;

import org.example.workflow.actions.Action;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a state in a workflow.
 * Each state defines possible transitions to other states and
 * actions that should be executed during those transitions.
 *
 * <p>States are connected through transitions that are triggered by events.
 * When a transition occurs, associated actions are executed.</p>
 *
 * @author Oliver-Victorxavier
 * @since 1.0
 */
public class State {
    private String name;
    private Map<String, String> transitions;
    private Map<String, Action> actions;

    /**
     * Creates a new state with the specified name.
     *
     * @param name A unique identifier for this state within its workflow
     */
    public State(String name) {
        this.name = name;
        this.transitions = new HashMap<>();
        this.actions = new HashMap<>();
    }

    /**
     * Defines a transition from this state to another state.
     *
     * @param event The event that triggers this transition
     * @param targetState The name of the target state
     * @return This state instance for method chaining
     */
    public State addTransition(String event, String targetState) {
        transitions.put(event, targetState);
        return this;
    }

    /**
     * Assigns an action to be executed when a specific event occurs.
     *
     * @param event The event that triggers the action
     * @param action The action to execute
     * @return This state instance for method chaining
     */
    public State addAction(String event, Action action) {
        actions.put(event, action);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getNextState(String event) {
        return transitions.get(event);
    }

    public Action getAction(String event) {
        return actions.get(event);
    }

    public boolean canHandle(String event) {
        return transitions.containsKey(event);
    }
}