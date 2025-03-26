package org.example.workflow.mediator;


import org.example.workflow.core.WorkflowInstance;
import org.example.workflow.observers.WorkflowObserver;

import java.util.ArrayList;
import java.util.List;

public class DefaultWorkflowMediator implements WorkflowMediator {
    private List<Object> components = new ArrayList<>();
    private List<WorkflowObserver> globalObservers = new ArrayList<>();

    @Override
    public void workflowStarted(WorkflowInstance instance) {
        // Add global observers to this instance
        for (WorkflowObserver observer : globalObservers) {
            instance.addObserver(observer);
        }

        System.out.println("Workflow started: " + instance.getWorkflow().getName() +
                " for " + instance.getEntity());
    }

    @Override
    public void workflowCompleted(WorkflowInstance instance) {
        System.out.println("Workflow completed: " + instance.getWorkflow().getName() +
                " for " + instance.getEntity() + " in state " + instance.getCurrentState());
    }

    @Override
    public void registerComponent(Object component) {
        components.add(component);

        // If it's an observer, add it to the global observers list
        if (component instanceof WorkflowObserver) {
            globalObservers.add((WorkflowObserver) component);
        }
    }
}