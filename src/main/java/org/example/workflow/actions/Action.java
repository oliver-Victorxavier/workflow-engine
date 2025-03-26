package org.example.workflow.actions;

import org.example.workflow.core.Context;
import org.example.workflow.core.WorkflowInstance;

public interface Action {
    void execute(WorkflowInstance instance, Context context);
}