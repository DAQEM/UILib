package com.daqem.uilib.api.client.gui.component.scroll;

import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import net.minecraft.client.gui.screens.Screen;

public interface IScrollBar {

    IScrollContent getContent();
    void setContent(IScrollContent content);
    ScrollOrientation getOrientation();
    void setOrientation(ScrollOrientation orientation);
    ScrollType getScrollType();
    void setScrollType(ScrollType scrollType);
    int getMinValue();
    int getMaxValue();
    void setMinValue(int minValue);
    void setMaxValue(int maxValue);
    int getValue();
    void setValue(int value);
    int getNextStep();
    int getPreviousStep();
    int getScrollDistance();

    void scroll(ScrollPaneComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta);
}
