package org.example.workflow.observers;

import org.example.workflow.core.Context;
import org.example.workflow.core.WorkflowInstance;

public class LoggingObserver implements WorkflowObserver {

    @Override
    public void onStateChange(WorkflowInstance instance, String fromState, String toState, String event, Context context) {
        System.out.println("[LOG] Workflow: " + instance.getWorkflow().getName() +
                " | Entity: " + instance.getEntity() +
                " | Event: " + event +
                " | From: " + fromState +
                " | To: " + toState +
                " | Actor: " + context.getActor());
    }
}