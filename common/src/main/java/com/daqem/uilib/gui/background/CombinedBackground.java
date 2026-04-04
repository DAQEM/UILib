package com.daqem.uilib.gui.background;

import com.daqem.uilib.api.background.IBackground;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jetbrains.annotations.NotNull;

public class CombinedBackground extends AbstractBackground {

    private final IBackground[] backgrounds;

    public CombinedBackground(IBackground... backgrounds) {
        this.backgrounds = backgrounds;
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (IBackground background : backgrounds) {
            background.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);
        }
    }
}
