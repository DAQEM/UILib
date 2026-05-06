package com.daqem.uilib.gui.component.color;

import com.daqem.uilib.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

public class GradientComponent extends AbstractComponent {

    private final int colorFrom;
    private final int colorTo;

    public GradientComponent(int x, int y, int width, int height, int colorFrom, int colorTo) {
        super(x, y, width, height);
        this.colorFrom = colorFrom;
        this.colorTo = colorTo;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        guiGraphics.fillGradient(
                getTotalX(),
                getTotalY(),
                getTotalX() + getWidth(),
                getTotalY() + getHeight(),
                colorFrom,
                colorTo
        );
    }
}
