package com.daqem.uilib.gui.widget;

import com.daqem.uilib.api.widget.ITextInputWidget;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.network.chat.Component;

public class MultiLineEditBoxWidget extends MultiLineEditBox implements IWidget, ITextInputWidget {

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title,
            int textColor,
            boolean textShadow,
            int cursorColor,
            boolean showBackground,
            boolean showDecorations
    ) {
        super(font, x, y, width, height, placeholder, title, textColor, textShadow, cursorColor, showBackground, showDecorations);
    }

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title,
            boolean showBackground,
            boolean showDecorations
    ) {
        super(font, x, y, width, height, placeholder, title, 0xFFE0E0E0, false, 0xFFD0D0D0, showBackground, showDecorations);
    }

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title,
            int textColor,
            boolean textShadow,
            int cursorColor
    ) {
        super(font, x, y, width, height, placeholder, title, textColor, textShadow, cursorColor, true, true);
    }

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title
    ) {
        super(font, x, y, width, height, placeholder, title, 0xFFE0E0E0, false, 0xFFD0D0D0, true, true);
    }

    public MultiLineEditBoxWidget(
            Component placeholder,
            Component title
    ) {
        super(Minecraft.getInstance().font, 0, 0, 200, 50, placeholder, title, 0xFFE0E0E0, false, 0xFFD0D0D0, true, true);
    }
}
