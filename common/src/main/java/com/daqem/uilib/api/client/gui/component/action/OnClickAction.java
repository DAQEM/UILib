package com.daqem.uilib.api.client.gui.component.action;

@FunctionalInterface
public interface OnClickAction<T> {

    void onClick(T clickedObject);
}