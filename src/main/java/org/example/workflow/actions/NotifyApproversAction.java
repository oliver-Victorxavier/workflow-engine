package org.example.workflow.actions;

import org.example.workflow.core.Context;
import org.example.workflow.core.WorkflowInstance;

public class NotifyApproversAction implements Action {
    @Override
    public void execute(WorkflowInstance instance, Context context) {
        // In a real application, this would send notifications to approvers
        System.out.println("Notifying approvers for " + instance.getEntity() +
                " submitted by " + context.getActor() +
                " with comment: " + context.getComment());
    }
}