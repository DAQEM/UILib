package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.text.IText;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class TextComponent extends AbstractComponent<TextComponent> {

    public TextComponent(int x, int y, @Nullable IText<?> text) {
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
