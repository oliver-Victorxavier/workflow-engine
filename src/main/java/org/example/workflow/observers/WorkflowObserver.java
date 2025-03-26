package org.example.workflow.observers;

import org.example.workflow.core.Context;
import org.example.workflow.core.WorkflowInstance;

public interface WorkflowObserver {
    void onStateChange(WorkflowInstance instance, String fromState, String toState, String event, Context context);
}