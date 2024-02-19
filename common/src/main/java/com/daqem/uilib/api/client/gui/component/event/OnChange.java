package com.daqem.uilib.api.client.gui.component.event;

@FunctionalInterface
public interface OnChange<T> {

    void onChange(T changedObject);
}
