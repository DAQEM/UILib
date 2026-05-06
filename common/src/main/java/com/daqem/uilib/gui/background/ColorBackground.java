package com.daqem.uilib.gui.background;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class ColorBackground extends AbstractBackground {

    private int color;

    public ColorBackground(int color) {
        this.color = color;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen instanceof Screen screen) {
            guiGraphics.fill(0, 0, screen.width, screen.height, color);
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
