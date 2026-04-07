package com.daqem.uilib.gui.background;

import com.daqem.uilib.api.screen.IScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class PanoramaBackground extends  AbstractBackground {

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen instanceof IScreenAccessor screen) {
            screen.uilib$extractPanoramaBackground(guiGraphics, partialTick);
        }
    }
}
