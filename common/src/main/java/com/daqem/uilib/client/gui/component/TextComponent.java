package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.gui.text.Text;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TextComponent extends AbstractComponent<TextComponent> {

    public TextComponent(Font font, Component text) {
        this(new Text(font, text));
    }

    public TextComponent(IText<?> text) {
        this(0, 0, text);
    }

    public TextComponent(int x, int y, IText<?> text) {
        super(null, x, y, 0, 0);
        setText(text);
        if (text != null) {
            setWidth(text.getWidth());
            setHeight(text.getHeight());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    }
}
