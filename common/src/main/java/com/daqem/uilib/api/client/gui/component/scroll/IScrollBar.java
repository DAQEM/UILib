package com.daqem.uilib.api.client.gui.component.scroll;

import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import net.minecraft.client.gui.screens.Screen;

public interface IScrollBar {

    int getMinValue(ScrollPaneComponent scrolledObject);
    int getMaxValue(ScrollPaneComponent scrolledObject);
    int getValue();
    void setValue(int value);
    int getNextStep(ScrollPaneComponent scrolledObject);
    int getPreviousStep(ScrollPaneComponent scrolledObject);
    void scroll(ScrollPaneComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta);
}
