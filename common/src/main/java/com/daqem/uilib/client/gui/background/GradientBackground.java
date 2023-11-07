package com.daqem.uilib.client.gui.background;

import net.minecraft.client.gui.GuiGraphics;

public class GradientBackground extends AbstractBackground<GradientBackground> {

    private int colorFrom;
    private int colorTo;

    public GradientBackground(int width, int height, int colorFrom, int colorTo) {
        super(width, height);
        this.colorFrom = colorFrom;
        this.colorTo = colorTo;
    }

    public GradientBackground(int x, int y, int width, int height, int colorFrom, int colorTo) {
        super(x, y, width, height);
        this.colorFrom = colorFrom;
        this.colorTo = colorTo;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.fillGradient(0, 0, getWidth(), getHeight(), colorFrom, colorTo);
    }

    public int getColorFrom() {
        return colorFrom;
    }

    public int getColorTo() {
        return colorTo;
    }

    public void setColorFrom(int colorFrom) {
        this.colorFrom = colorFrom;
    }

    public void setColorTo(int colorTo) {
        this.colorTo = colorTo;
    }
}
