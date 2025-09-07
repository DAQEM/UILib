package com.daqem.uilib.gui.background;

import com.daqem.uilib.api.background.IBackground;
import net.minecraft.client.gui.GuiGraphics;

public class CombinedBackground extends AbstractBackground {

    private final IBackground[] backgrounds;

    public CombinedBackground(IBackground... backgrounds) {
        this.backgrounds = backgrounds;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (IBackground background : backgrounds) {
            background.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }
}
