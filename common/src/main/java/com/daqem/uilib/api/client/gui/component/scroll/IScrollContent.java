package com.daqem.uilib.api.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.IComponent;

import java.util.List;

public interface IScrollContent {

    List<IComponent<?>> getComponents();
    void addComponent(IComponent<?> component);
    void removeComponent(IComponent<?> component);
    void clearComponents();

    int getContentSpacing();
    void setContentSpacing(int contentSpacing);

    ScrollOrientation getOrientation();
    void setOrientation(ScrollOrientation orientation);
}
