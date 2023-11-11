package com.daqem.uilib.client.gui.text;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class Text extends AbstractText<Text> {

    @SuppressWarnings("unused")
    public Text(Font font, Component text, int x, int y) {
        super(font, text, x, y);
    }

    @SuppressWarnings("unused")
    public Text(Font font, Component text, int x, int y, int width, int height) {
        super(font, text, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.drawString(getFont(), getText(), 0, 0, getTextColor(), isShadow());
    }
}
