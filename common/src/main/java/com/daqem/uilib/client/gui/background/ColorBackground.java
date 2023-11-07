package com.daqem.uilib.client.gui.background;

import net.minecraft.client.gui.GuiGraphics;

import java.awt.Color;

public class ColorBackground extends AbstractBackground<ColorBackground> {

    private int color;

    public ColorBackground(int width, int height, int color) {
        super(width, height);
        this.color = color;
    }

    public ColorBackground(int x, int y, int width, int height, int color) {
        super(x, y, width, height);
        this.color = color;
    }

    public ColorBackground(int width, int height, Color color) {
        super(width, height);
        this.color = color.getRGB();
    }

    public ColorBackground(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color.getRGB();
    }

    public ColorBackground(int width, int height, int red, int green, int blue, int opacity) {
        super(width, height);
        this.color = new Color(red, green, blue, opacity).getRGB();
    }

    public ColorBackground(int x, int y, int width, int height, int red, int green, int blue, int opacity) {
        super(x, y, width, height);
        this.color = new Color(red, green, blue, opacity).getRGB();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.fill(0, 0, getWidth(), getHeight(), color);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
