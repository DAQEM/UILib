package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;

public interface IHoverable<T extends IHoverable<T>> {

    boolean isHovered(double mouseX, double mouseY);
    void preformOnHoverEvent(double mouseX, double mouseY);
    OnHoverEvent<T> getOnHoverEvent();
    void setOnHoverEvent(OnHoverEvent<T> onHoverEvent);
    void setHoverState(T component);
    T getHoverState();
}
