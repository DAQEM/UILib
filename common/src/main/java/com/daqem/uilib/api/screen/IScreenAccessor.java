package com.daqem.uilib.api.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;

import java.util.List;

public interface IScreenAccessor {

    List<Renderable> uilib$getRenderables();

    List<GuiEventListener> uilib$getChildren();

    List<NarratableEntry> uilib$getNarratables();

    void uilib$removeWidget(GuiEventListener widget);

    void uilib$extractBlurredBackground(GuiGraphicsExtractor guiGraphics);

    void uilib$extractPanoramaBackground(GuiGraphicsExtractor guiGraphics, float partialTicks);
}
