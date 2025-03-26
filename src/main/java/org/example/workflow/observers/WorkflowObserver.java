package org.example.workflow.observers;

import org.example.workflow.core.Context;
import org.example.workflow.core.WorkflowInstance;

/**
 * Observer interface for monitoring workflow state changes.
 * Implementing classes will be notified when significant events
 * occur within a workflow instance.
 *
 * <p>This interface is part of the Observer pattern implementation
 * that allows components to react to workflow state changes.</p>
 *
 * @author Oliver-Victorxavier
 * @since 1.0
 */
public interface WorkflowObserver {

    /**
     * Called when the state of a workflow instance changes.
     *
     * @param instance The workflow instance that changed state
     * @param fromState The previous state of the instance
     * @param toState The new state of the instance
     * @param event The event that triggered the state change
     * @param context The context containing data for this transition
     */
    void onStateChange(WorkflowInstance instance, String fromState, String toState, String event, Context context);
}