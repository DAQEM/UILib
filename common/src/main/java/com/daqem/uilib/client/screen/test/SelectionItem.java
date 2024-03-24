package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.selection.ISelectionItem;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import net.minecraft.network.chat.Component;

public class SelectionItem implements ISelectionItem<ButtonComponent> {

    private final int height;
    private final Component name;
    private final Component description;
    private final OnClickEvent<ButtonComponent> onClickEvent;

    public SelectionItem(int height, Component name, Component description, OnClickEvent<ButtonComponent> onClickEvent) {
        this.height = height;
        this.name = name;
        this.description = description;
        this.onClickEvent = onClickEvent;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Component getName() {
        return this.name;
    }

    public Component getDescription() {
        return this.description;
    }

    @Override
    public OnClickEvent<ButtonComponent> getOnClickEvent() {
        return this.onClickEvent;
    }
}
