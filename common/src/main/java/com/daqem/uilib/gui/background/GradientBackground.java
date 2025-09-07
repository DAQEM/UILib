package com.daqem.uilib.gui.background;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class GradientBackground extends AbstractBackground {

    private int colorFrom;
    private int colorTo;

    public GradientBackground(int colorFrom, int colorTo) {
        this.colorFrom = colorFrom;
        this.colorTo = colorTo;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen instanceof Screen screen) {
            guiGraphics.fillGradient(0, 0, screen.width, screen.height, colorFrom, colorTo);
        }
    }

    public int getColorFrom() {
        return colorFrom;
    }

    public void setColorFrom(int colorFrom) {
        this.colorFrom = colorFrom;
    }

    public int getColorTo() {
        return colorTo;
    }

    public void setColorTo(int colorTo) {
        this.colorTo = colorTo;
    }
}
