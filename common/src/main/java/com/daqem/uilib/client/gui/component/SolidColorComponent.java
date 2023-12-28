package com.daqem.uilib.client.gui.component;

import net.minecraft.client.gui.GuiGraphics;

public class SolidColorComponent extends AbstractComponent<SolidColorComponent> {

    private final int color;

    public SolidColorComponent(int x, int y, int width, int height, int color) {
        super(null, x, y, width, height);
        this.color = color;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), color);
    }
}
