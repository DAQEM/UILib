package com.daqem.uilib.gui.component.text.multiline;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

public class MultiLineTextComponent extends AbstractMultiLineTextComponent{

    public MultiLineTextComponent(int x, int y, int maxWidth, Component text) {
        super(x, y, maxWidth, text);
    }

    public MultiLineTextComponent(int x, int y, int maxWidth, Component text, int color) {
        super(x, y, maxWidth, text, color);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        for (int i = 0; i < getLines().size(); i++) {
            guiGraphics.drawString(
                    getFont(),
                    getLines().get(i),
                    getTotalX(),
                    getTotalY() + i * getFont().lineHeight,
                    getColor(),
                    isDrawShadow()
            );
        }

        if (isRenderDebugBorder()) {
            guiGraphics.hLine(getTotalX() + getUnusedSpaceX(), getTotalX() + getMaxWidth() + getUnusedSpaceX() - 1, getTotalY(), 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + getMaxWidth() + getUnusedSpaceX() - 1, getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.hLine(getTotalX() + getUnusedSpaceX(), getTotalX() + getMaxWidth() + getUnusedSpaceX() - 1, getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + getUnusedSpaceX(), getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
        }
    }
}
