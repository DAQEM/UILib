package com.daqem.uilib.gui.component.text;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TruncatedTextComponent extends AbstractSingleLineTextComponent{

    private int maxWidth;
    private String ellipsis = "...";

    private Component backupText;

    public TruncatedTextComponent(int x, int y, int maxWidth, Component text) {
        super(x, y, text);
        this.maxWidth = maxWidth;
        this.backupText = text;
        setTruncatedText();
    }

    public TruncatedTextComponent(int x, int y, int maxWidth, Component text, int color) {
        super(x, y, text, color);
        this.maxWidth = maxWidth;
        this.backupText = text;
        setTruncatedText();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        guiGraphics.drawString(
                this.getFont(),
                this.getText(),
                this.getTotalX(),
                this.getTotalY(),
                this.getColor(),
                this.isDrawShadow()
        );

        if (isRenderDebugBorder()) {
            guiGraphics.hLine(getTotalX() + getUnusedSpaceX(), getTotalX() + maxWidth + getUnusedSpaceX() - 1, getTotalY(), 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + maxWidth + getUnusedSpaceX() - 1, getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.hLine(getTotalX() + getUnusedSpaceX(), getTotalX() + maxWidth + getUnusedSpaceX() - 1, getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + getUnusedSpaceX(), getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
        }
    }

    protected int getUnusedSpaceX() {
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
        setTruncatedText();
    }

    public String getEllipsis() {
        return ellipsis;
    }

    public void setEllipsis(String ellipsis) {
        this.ellipsis = ellipsis;
        setTruncatedText();
    }

    private void setTruncatedText() {
        String text = backupText.getString();
        Font font = getFont();
        if (font.width(text) <= maxWidth) {
            setText(backupText);
            return;
        }
        int ellipsisWidth = font.width(getEllipsis());
        int availableWidth = maxWidth - ellipsisWidth;
        StringBuilder truncated = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (font.width(truncated.toString() + c) > availableWidth) {
                break;
            }
            truncated.append(c);
        }
        truncated.append(getEllipsis());
        setText(Component.literal(truncated.toString()).setStyle(this.getText().getStyle()));
    }
}
