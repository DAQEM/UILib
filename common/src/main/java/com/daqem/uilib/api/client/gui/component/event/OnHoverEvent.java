package com.daqem.uilib.api.client.gui.component.event;

import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnHoverEvent<T> {

    void onHover(T hoveredObject, Screen screen, double mouseX, double mouseY, float delta);
}
