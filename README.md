# Workflow Engine

A workflow engine implementation using State, Command, Observer, and Mediator design patterns.

## Overview

This project implements a flexible workflow engine that allows modeling and executing business processes. It provides a framework for defining workflow states, transitions between states, and actions to be executed during those transitions.

## Design Patterns

The implementation leverages the following design patterns:

- **State Pattern**: Models workflow states and transitions
- **Command Pattern**: Encapsulates actions to be executed during transitions
- **Observer Pattern**: Notifies interested components about state changes
- **Mediator Pattern**: Coordinates communication between workflow components

## Usage Example

```java
// Define workflow
Workflow approvalFlow = new Workflow("DocumentApproval");
approvalFlow.addState(new State("DRAFT")
    .addTransition("submit", "PENDING_APPROVAL")
    .addAction("submit", new NotifyApproversAction()));

approvalFlow.addState(new State("PENDING_APPROVAL")
    .addTransition("approve", "APPROVED")
    .addTransition("reject", "REJECTED"));

// Start workflow for a document
WorkflowInstance instance = workflowEngine.startWorkflow(
    approvalFlow, 
    new Document("Q1 Report")
);

// Execute transition
instance.trigger("submit", 
    new Context().withActor(currentUser).withComment("Ready for review")
);