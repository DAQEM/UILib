package com.daqem.uilib.api.client.gui.component.io;

import net.minecraft.network.chat.Component;

public interface IIOComponentEntry<T> {

    String stringValue();

    Component component();

    T value();
}
