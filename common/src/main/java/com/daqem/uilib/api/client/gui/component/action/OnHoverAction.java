package com.daqem.uilib.api.client.gui.component.action;

@FunctionalInterface
public interface OnHoverAction<T> {

    void onHover(T hoveredObject);
}
