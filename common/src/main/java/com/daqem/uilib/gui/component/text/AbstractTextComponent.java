package com.daqem.uilib.gui.component.text;

import com.daqem.uilib.gui.component.AbstractComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public abstract class AbstractTextComponent extends AbstractComponent {

    private Component text;
    private int color;
    private boolean drawShadow = false;
    private TextAlign textAlign = TextAlign.LEFT;
    private Font font = Minecraft.getInstance().font;

    public AbstractTextComponent(int x, int y, int width, int height, Component text) {
        this(x, y, width, height, text, 0xFFFFFFFF);
    }

    public AbstractTextComponent(int x, int y, int width, int height, Component text, int color) {
        super(x, y, width, height);
        this.text = text;
        this.color = color;
    }

    @Override
    public int getTotalX() {
        switch (getTextAlign()) {
            case CENTER -> {
                return super.getTotalX() - (getWidth() / 2);
            }
            case RIGHT -> {
                return super.getTotalX() - getWidth();
            }
            case LEFT -> {
                return super.getTotalX();
            }
        }
        return super.getTotalX();
    }

    public Component getText() {
        return text;
    }

    public void setText(Component text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isDrawShadow() {
        return drawShadow;
    }

    public void setDrawShadow(boolean drawShadow) {
        this.drawShadow = drawShadow;
    }

    public TextAlign getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(TextAlign textAlign) {
        this.textAlign = textAlign;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
