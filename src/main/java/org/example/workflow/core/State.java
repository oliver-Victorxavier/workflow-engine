package org.example.workflow.core;

import org.example.workflow.actions.Action;

import java.util.HashMap;
import java.util.Map;

public class State {
    private String name;
    private Map<String, String> transitions;
    private Map<String, Action> actions;

    public State(String name) {
        this.name = name;
        this.transitions = new HashMap<>();
        this.actions = new HashMap<>();
    }

    public State addTransition(String event, String targetState) {
        transitions.put(event, targetState);
        return this;
    }

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