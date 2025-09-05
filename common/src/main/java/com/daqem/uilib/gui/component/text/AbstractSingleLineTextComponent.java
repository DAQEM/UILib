package com.daqem.uilib.gui.component.text;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public abstract class AbstractSingleLineTextComponent extends AbstractTextComponent {

    public AbstractSingleLineTextComponent(int x, int y, Component text) {
        super(x, y, 0, 0, text);
        updateSize();
    }

    public AbstractSingleLineTextComponent(int x, int y, Component text, int color) {
        super(x, y, 0, 0, text, color);
        updateSize();
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        updateSize();
    }

    @Override
    public void setText(Component text) {
        super.setText(text);
        updateSize();
    }

    protected void updateSize() {
        setWidth(getFont().width(getText()));
        setHeight(getFont().lineHeight);
    }
}
