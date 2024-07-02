package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnCharTypedEvent;

public interface ICharTypable<T extends ICharTypable<T>> {

    boolean preformOnCharTypedEvent(char typedChar, int modifiers);

    OnCharTypedEvent<T> getOnCharTypedEvent();

    void setOnCharTypedEvent(OnCharTypedEvent<T> onCharTypedEvent);
}
