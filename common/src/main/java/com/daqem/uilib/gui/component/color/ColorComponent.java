package com.daqem.uilib.gui.component.color;

import com.daqem.uilib.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

public class ColorComponent extends AbstractComponent {

    private final int color;

    public ColorComponent(int x, int y, int width, int height, int color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        guiGraphics.fill(
                this.getTotalX(),
                this.getTotalY(),
                this.getTotalX() + this.getWidth(),
                this.getTotalY() + this.getHeight(),
                color
        );
    }
}
