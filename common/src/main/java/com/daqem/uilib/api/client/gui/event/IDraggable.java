package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnDragEvent;

public interface IDraggable<T extends IDraggable<T>> {

    boolean isDragged(double mouseX, double mouseY, int button, double dragX, double dragY);

    void preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY);

    OnDragEvent<T> getOnDragEvent();

    void setOnDragEvent(OnDragEvent<T> onDragEvent);
}
