package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.component.action.OnHoverAction;
import com.daqem.uilib.api.client.gui.component.IComponent;

public interface IHoverable<T extends IHoverable<T>> {

    /**
     * Determines if the given coordinates are within the bounds of the component,
     * indicating that the component is being hovered.
     *
     * @param mouseX the x-coordinate of the mouse
     * @param mouseY the y-coordinate of the mouse
     * @return {@code true} if the component is being hovered, {@code false} otherwise
     */
    boolean isHovered(double mouseX, double mouseY);

    /**
     * Triggers the {@link OnHoverAction} for the hovered component.
     * <p/>
     * <p>The onHover action is defined by implementing the {@link OnHoverAction} interface with the desired functionality.
     * When a component is hovered, this method is called to perform the associated action.</p>
     * <p/>
     * <p>Note: The {@link OnHoverAction#onHover(Object)} action is registered using the {@link IHoverable#setOnHoverAction(OnHoverAction)} method of the {@link IComponent} interface.</p>
     * <p/>
     * <p><b>Example usage:</b></p>
     * <pre>{@code
     * // Create a custom onHover action
     * OnHoverAction customAction = (component) -> {
     *     // Perform the desired action for the hovered component
     *     component.doSomething();
     * };
     *
     * // Register the custom action with the component
     * component.setOnHoverAction(customAction);
     *
     * // When the component is hovered, the custom action will be triggered
     * component.preformOnHoverAction();
     * }</pre>
     */
    void preformOnHoverAction();

    /**
     * Retrieves the action performed when hovering over the component.
     *
     * @return the {@link OnHoverAction} that will be performed when hovering over the component
     */
    OnHoverAction<T> getOnHoverAction();

    /**
     * Sets the action to be performed when the component is being hovered over.
     *
     * @param onHoverAction the action to be performed when hovering
     */
    void setOnHoverAction(OnHoverAction<T> onHoverAction);

    void setHoverState(T component);

    T getHoverState();
}
