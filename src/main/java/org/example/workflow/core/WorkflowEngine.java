package org.example.workflow.core;

import org.example.workflow.mediator.WorkflowMediator;

import java.util.HashMap;
import java.util.Map;

public class WorkflowEngine {
    private Map<String, Workflow> workflows;
    private Map<String, WorkflowInstance> instances;
    private WorkflowMediator mediator;

    public WorkflowEngine(WorkflowMediator mediator) {
        this.workflows = new HashMap<>();
        this.instances = new HashMap<>();
        this.mediator = mediator;
    }

    public void registerWorkflow(Workflow workflow) {
        workflows.put(workflow.getName(), workflow);
    }

    public WorkflowInstance startWorkflow(Workflow workflow, Object entity) {
        WorkflowInstance instance = new WorkflowInstance(workflow, entity);

        // Register instance
        instances.put(instance.getId(), instance);

        // Notify mediator
        mediator.workflowStarted(instance);

        return instance;
    }

    public WorkflowInstance getInstance(String instanceId) {
        return instances.get(instanceId);
    }

    public Workflow getWorkflow(String name) {
        return workflows.get(name);
    }
}