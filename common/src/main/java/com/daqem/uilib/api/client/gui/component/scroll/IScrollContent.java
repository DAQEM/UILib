package com.daqem.uilib.api.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.IComponent;

import java.util.List;

public interface IScrollContent {

    int getContentSpacing();
    void setContentSpacing(int contentSpacing);

    ScrollOrientation getOrientation();
    void setOrientation(ScrollOrientation orientation);
}
