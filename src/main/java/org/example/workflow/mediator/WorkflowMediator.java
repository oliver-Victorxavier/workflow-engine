package org.example.workflow.mediator;

import org.example.workflow.core.WorkflowInstance;

public interface WorkflowMediator {
    void workflowStarted(WorkflowInstance instance);
    void workflowCompleted(WorkflowInstance instance);
    void registerComponent(Object component);
}