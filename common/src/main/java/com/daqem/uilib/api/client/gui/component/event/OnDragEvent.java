package com.daqem.uilib.api.client.gui.component.event;

import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnDragEvent<T> {

    void onDrag(T draggedObject, Screen screen, double mouseX, double mouseY, int button, double dragX, double dragY);
}
