package com.daqem.uilib.api.client.gui.component.event;

import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnScrollEvent<T> {

    boolean onScroll(T scrolledObject, Screen screen, double mouseX, double mouseY, double amountX, double amountY);
}
