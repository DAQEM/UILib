package com.daqem.uilib.gui.background;

import com.daqem.uilib.api.screen.IScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class BlurredBackground extends AbstractBackground {

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen instanceof IScreenAccessor screen) {
            screen.uilib$extractBlurredBackground(guiGraphics, partialTick);
        }
    }
}
