package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnMouseReleaseEvent;

public interface IMouseReleasable<T extends IMouseReleasable<T>> {

    boolean preformOnMouseReleaseEvent(double mouseX, double mouseY, int button);

    OnMouseReleaseEvent<T> getOnMouseReleaseEvent();

    void setOnMouseReleaseEvent(OnMouseReleaseEvent<T> onMouseReleaseEvent);
}
