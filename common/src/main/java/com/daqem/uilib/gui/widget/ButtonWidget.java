package com.daqem.uilib.gui.widget;

import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ButtonWidget extends Button implements IWidget {

    public ButtonWidget(Component message) {
        this(0, 0, message);
    }

    public ButtonWidget(int x, int y, Component message) {
        this(x, y, Button.DEFAULT_WIDTH, message);
    }

    public ButtonWidget(int x, int y, int width, Component message) {
        this(x, y, width, Button.DEFAULT_HEIGHT, message);
    }

    public ButtonWidget(int x, int y, int width, int height, Component message) {
        this(x, y, width, height, message, button -> {
            // Default action does nothing
        });
    }

    public ButtonWidget(int x, int y, int width, int height, Component message, OnPress onPress) {
        this(x, y, width, height, message, onPress, Button.DEFAULT_NARRATION);
    }

    public ButtonWidget(int x, int y, int width, int height, Component message, OnPress onPress, CreateNarration createNarration) {
        super(x, y, width, height, message, onPress, createNarration);
    }
}
