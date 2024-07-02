package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnKeyPressedEvent;

public interface IKeyPressable<T extends IKeyPressable<T>> {

    boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers);

    OnKeyPressedEvent<T> getOnKeyPressedEvent();

    void setOnKeyPressedEvent(OnKeyPressedEvent<T> onKeyPressedEvent);
}
