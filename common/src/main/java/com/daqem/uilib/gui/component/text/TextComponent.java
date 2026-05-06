package com.daqem.uilib.gui.component.text;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TextComponent extends AbstractSingleLineTextComponent {

    public TextComponent(int x, int y, Component text) {
        super(x, y, text);
    }

    public TextComponent(int x, int y, Component text, int color) {
        super(x, y, text, color);
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
    }
}
