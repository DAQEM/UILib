package com.daqem.uilib.api.widget;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import org.jetbrains.annotations.NotNull;

public interface IWidget extends Renderable, GuiEventListener, LayoutElement, NarratableEntry {

    @Override
    default @NotNull ScreenRectangle getRectangle() {
        return LayoutElement.super.getRectangle();
    }

    /*
     * This has been implemented in the AbstractWidgetMixin.
     * Adding default methods here to avoid compilation errors because you should add IWidget to your widget class.
     */
    default int uilib$getParentX() {
        return 0;
    }
    default int uilib$getParentY() {
        return 0;
    }
    default void uilib$updateParentPosition(int parentX, int parentY) {
    }
}
