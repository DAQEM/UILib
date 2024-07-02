package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.api.client.gui.component.io.IIOComponentEntry;
import net.minecraft.network.chat.Component;

public record IOComponentEntry<T>(String stringValue, Component component, T value) implements IIOComponentEntry<T> {

    @Override
    public String stringValue() {
        return stringValue;
    }

    @Override
    public Component component() {
        return component;
    }

    @Override
    public T value() {
        return value;
    }
}
