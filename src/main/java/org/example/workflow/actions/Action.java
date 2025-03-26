package org.example.workflow.actions;

import org.example.workflow.core.Context;
import org.example.workflow.core.WorkflowInstance;

/**
 * Represents an action that can be executed during a workflow state transition.
 * Implements the Command pattern to encapsulate operations that occur when
 * a workflow transitions between states.
 *
 * <p>Actions allow for extending the workflow behavior without modifying
 * the core workflow components.</p>
 *
 * @author Oliver-Victorxavier
 * @since 1.0
 */
public interface Action {
    /**
     * Executes this action during a workflow transition.
     *
     * @param instance The workflow instance that is transitioning
     * @param context The context containing data relevant to this transition
     */
    void execute(WorkflowInstance instance, Context context);
}