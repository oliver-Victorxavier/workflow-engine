package org.example.workflow.core;

import org.example.workflow.actions.Action;
import org.example.workflow.observers.WorkflowObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a running instance of a workflow for a specific entity.
 * Tracks the current state of the entity and handles transitions
 * between states based on triggered events.
 *
 * <p>Each entity being processed by a workflow has its own workflow instance.</p>
 *
 * @author Oliver-Victorxavier
 * @since 1.0
 */
public class WorkflowInstance {
    private String id;
    private Workflow workflow;
    private String currentState;
    private Object entity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<WorkflowObserver> observers = new ArrayList<>();

    public WorkflowInstance(Workflow workflow, Object entity) {
        this.id = UUID.randomUUID().toString();
        this.workflow = workflow;
        this.entity = entity;
        this.currentState = workflow.getInitialState();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public boolean trigger(String event, Context context) {
        State state = workflow.getState(currentState);

        if (!state.canHandle(event)) {
            return false;
        }

        // Get next state
        String nextState = state.getNextState(event);

        // Execute action if exists
        Action action = state.getAction(event);
        if (action != null) {
            action.execute(this, context);
        }

        // Update state
        String previousState = currentState;
        currentState = nextState;
        updatedAt = LocalDateTime.now();

        // Notify observers about state change
        notifyObservers(previousState, currentState, event, context);

        return true;
    }

    public void addObserver(WorkflowObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WorkflowObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String fromState, String toState, String event, Context context) {
        for (WorkflowObserver observer : observers) {
            observer.onStateChange(this, fromState, toState, event, context);
        }
    }

    public String getId() {
        return id;
    }

    public String getCurrentState() {
        return currentState;
    }

    public Object getEntity() {
        return entity;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}