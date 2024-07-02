package com.daqem.uilib.api.client.gui.component.event;

import com.daqem.uilib.api.client.gui.event.IKeyPressable;
import net.minecraft.client.gui.screens.Screen;

@FunctionalInterface
public interface OnKeyPressedEvent<T extends IKeyPressable<T>> {

    boolean onKeyPressed(T typedObject, Screen screen, int keyCode, int scanCode, int modifiers);
}
