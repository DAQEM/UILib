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
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        Component text = getText();
        Font font = getFont();
        int width = getWidth();

        if (textExceedsWidth(text, font, width)) {
            text = trimTextToFitWidth(text, font, width);
            setText(text);
        }

        renderText(graphics);
    }

    private boolean textExceedsWidth(Component text, Font font, int width) {
        return font.width(text) > width;
    }

    private Component trimTextToFitWidth(Component text, Font font, int width) {
        text = text.plainCopy().append(ending).withStyle(style -> getText().getStyle());

        while (font.width(text) > width && text.getString().length() > (1 + ending.length())) {
            String trimmedText = text.getString().substring(0, text.getString().length() - (1 + ending.length()));
            text = Component.literal(trimmedText).append(ending).withStyle(style -> getText().getStyle());
        }

        return text;
    }

    private void renderText(GuiGraphics graphics) {
        graphics.drawString(getFont(), getText(), 0, 0, getTextColor(), isShadow());
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }
}
