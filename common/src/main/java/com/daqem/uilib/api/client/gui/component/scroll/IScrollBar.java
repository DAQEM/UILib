package com.daqem.uilib.api.client.gui.component.scroll;

import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import net.minecraft.client.gui.screens.Screen;

public interface IScrollBar {

    int getMinValue(ScrollPaneComponent scrollPaneComponent);
    int getMaxValue(ScrollPaneComponent scrollPaneComponent);
    int getValue();
    void setValue(int value);
    int getNextStep(ScrollPaneComponent scrollPaneComponent);
    int getPreviousStep(ScrollPaneComponent scrollPaneComponent);
    void scroll(ScrollPaneComponent scrollPaneComponent, Screen screen, double mouseX, double mouseY, double delta);
}
