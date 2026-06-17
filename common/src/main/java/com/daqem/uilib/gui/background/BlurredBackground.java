package com.daqem.uilib.gui.background;

import com.daqem.uilib.api.screen.IScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jetbrains.annotations.NotNull;

public class BlurredBackground extends AbstractBackground {

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().gui.screen() instanceof IScreenAccessor screen) {
            screen.uilib$extractBlurredBackground(guiGraphics);
        }
    }
}
