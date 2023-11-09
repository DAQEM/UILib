package com.daqem.uilib.api.client.gui.component.action;

import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnClickAction<T> {

    void onClick(T clickedObject, Screen screen, double mouseX, double mouseY);
}