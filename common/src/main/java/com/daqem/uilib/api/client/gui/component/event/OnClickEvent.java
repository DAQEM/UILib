package com.daqem.uilib.api.client.gui.component.event;

import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnClickEvent<T> {

    void onClick(T clickedObject, Screen screen, double mouseX, double mouseY, int button);
}