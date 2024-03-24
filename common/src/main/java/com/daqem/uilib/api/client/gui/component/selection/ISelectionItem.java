package com.daqem.uilib.api.client.gui.component.selection;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import net.minecraft.network.chat.Component;

public interface ISelectionItem<T> {

    int getHeight();
    Component getName();
    Component getDescription();
    OnClickEvent<T> getOnClickEvent();
}
