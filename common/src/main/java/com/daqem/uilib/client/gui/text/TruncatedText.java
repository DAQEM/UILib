package com.daqem.uilib.client.gui.text;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TruncatedText extends AbstractText<TruncatedText> {

    private String ending = "...";

    public TruncatedText(Font font, Component text, int x, int y) {
        super(font, text, x, y);
    }

    public TruncatedText(Font font, Component text, int x, int y, int width, int height) {
        super(font, text, x, y, width, height);
    }

    public TruncatedText(Font font, Component text, int x, int y, String ending) {
        super(font, text, x, y);
        this.ending = ending;
    }

    public TruncatedText(Font font, Component text, int x, int y, int width, int height, String ending) {
        super(font, text, x, y, width, height);
        this.ending = ending;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        Component text = getText();
        Font font = getFont();
        int width = getWidth();
        int textWidth = font.width(text);
        if (textWidth > width) {

            text = text.plainCopy().append(ending).withStyle(style -> getText().getStyle());

            while (font.width(text) > width) {
                text = Component.literal(text.getString().substring(0, text.getString().length() - (1 + ending.length()))).append(ending).withStyle(style -> getText().getStyle());
            }

            setText(text);

        }
        graphics.drawString(getFont(), getText(), 0, 0, getTextColor(), isShadow());
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }
}
