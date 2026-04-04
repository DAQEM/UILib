package com.daqem.uilib.gui.component;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class EmptyComponent extends AbstractComponent{

    public EmptyComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}
