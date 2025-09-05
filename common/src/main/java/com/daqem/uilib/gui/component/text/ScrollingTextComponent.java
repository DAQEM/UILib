package com.daqem.uilib.gui.component.text;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class ScrollingTextComponent extends AbstractSingleLineTextComponent {

    private int maxWidth;

    public ScrollingTextComponent(int x, int y, int maxWidth, Component text) {
        super(x, y, text);
        this.maxWidth = maxWidth;
    }

    public ScrollingTextComponent(int x, int y, int maxWidth, Component text, int color) {
        super(x, y, text, color);
        this.maxWidth = maxWidth;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        if (getWidth() > maxWidth) {
            guiGraphics.enableScissor(
                    getTotalX() + getXOffset(),
                    getTotalY(),
                    getTotalX() + maxWidth + getXOffset(),
                    getTotalY() + getHeight()
            );
            int hiddenWidth = getWidth() - maxWidth;
            double currentTimeSeconds = Util.getMillis() / 1000.0;
            double animationDuration = Math.max(hiddenWidth * 0.5, 3.0);
            double animationFactor = Math.sin((Math.PI / 2) * Math.cos((Math.PI * 2) * currentTimeSeconds / animationDuration)) / 2.0 + 0.5;
            double scrollOffset = Mth.lerp(animationFactor, 0.0, hiddenWidth);
            drawText(guiGraphics, (int) scrollOffset - getXOffset());
            guiGraphics.disableScissor();
        } else {
            drawText(guiGraphics, 0);
        }

        if (isRenderDebugBorder()) {
            guiGraphics.hLine(getTotalX() + getXOffset(), getTotalX() + maxWidth + getXOffset() - 1, getTotalY(), 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + maxWidth + getXOffset() - 1, getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.hLine(getTotalX() + getXOffset(), getTotalX() + maxWidth + getXOffset() - 1, getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + getXOffset(), getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
        }
    }

    protected int getXOffset() {
        switch (getTextAlign()) {
            case CENTER -> {
                return (getWidth() - maxWidth) / 2;
            }
            case RIGHT -> {
                return getWidth() - maxWidth;
            }
            default -> {
                return 0;
            }
        }
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    private void drawText(GuiGraphics guiGraphics, int scrollOffset) {
        guiGraphics.drawString(
                this.getFont(),
                this.getText(),
                this.getTotalX() - scrollOffset,
                this.getTotalY(),
                this.getColor(),
                this.isDrawShadow()
        );
    }
}
