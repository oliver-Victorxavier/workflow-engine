package org.example.workflow;

import org.example.workflow.actions.NotifyApproversAction;
import org.example.workflow.core.*;
import org.example.workflow.mediator.DefaultWorkflowMediator;
import org.example.workflow.mediator.WorkflowMediator;
import org.example.workflow.model.Document;
import org.example.workflow.observers.LoggingObserver;

public class Main {
    public static void main(String[] args) {
        // Create mediator
        WorkflowMediator mediator = new DefaultWorkflowMediator();

        // Create and register a global observer
        LoggingObserver loggingObserver = new LoggingObserver();
        mediator.registerComponent(loggingObserver);

        // Create workflow engine with mediator
        WorkflowEngine workflowEngine = new WorkflowEngine(mediator);

        // Define workflow
        Workflow approvalFlow = new Workflow("DocumentApproval");
        approvalFlow.addState(new State("DRAFT")
                .addTransition("submit", "PENDING_APPROVAL")
                .addAction("submit", new NotifyApproversAction()));

        approvalFlow.addState(new State("PENDING_APPROVAL")
                .addTransition("approve", "APPROVED")
                .addTransition("reject", "REJECTED"));

        approvalFlow.addState(new State("APPROVED"));
        approvalFlow.addState(new State("REJECTED"));

        // Register workflow
        workflowEngine.registerWorkflow(approvalFlow);

        // Create a user
        User currentUser = new User("john.doe");

        // Iniciar workflow para um documento
        Document document = new Document("Q1 Report", "Financial report for Q1 2025");
        WorkflowInstance instance = workflowEngine.startWorkflow(
                approvalFlow,
                document
        );

        System.out.println("Initial state: " + instance.getCurrentState());

        // Execute transition
        instance.trigger("submit",
                new Context().withActor(currentUser).withComment("Ready for review")
        );

        System.out.println("State after submit: " + instance.getCurrentState());

        // Approve the document
        User manager = new User("manager");
        instance.trigger("approve",
                new Context().withActor(manager).withComment("Looks good")
        );

        System.out.println("Final state: " + instance.getCurrentState());
    }

    // Simple User class for the example
    static class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return username;
        }
    }
}