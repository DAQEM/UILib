package com.daqem.uilib.api.client.gui.component.scroll;

import com.daqem.uilib.client.gui.component.scroll.ScrollPanelComponent;
import net.minecraft.client.gui.screens.Screen;

public interface IScrollBar {

    int getMinValue(ScrollPanelComponent scrollPanelComponent);
    int getMaxValue(ScrollPanelComponent scrollPanelComponent);
    int getScrollValue();
    void setScrollValue(int scrollValue);
    int getNextStep(ScrollPanelComponent scrollPanelComponent);
    int getPreviousStep(ScrollPanelComponent scrollPanelComponent);
    void scroll(ScrollPanelComponent scrollPanelComponent, double delta);
    int getDimension(boolean isHorizontal);
}
