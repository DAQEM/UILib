package com.daqem.uilib.api.client.gui.component.event;

import net.minecraft.client.gui.screens.Screen;

public interface OnCharTypedEvent<T > {

    boolean onCharTyped(T typedObject, Screen screen, char typedChar, int modifiers);
}
