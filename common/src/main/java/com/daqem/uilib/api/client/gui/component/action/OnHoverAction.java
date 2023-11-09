package com.daqem.uilib.api.client.gui.component.action;

import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnHoverAction<T> {

    void onHover(T hoveredObject, Screen screen, double mouseX, double mouseY);
}
