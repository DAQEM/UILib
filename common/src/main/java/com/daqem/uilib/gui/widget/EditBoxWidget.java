package com.daqem.uilib.gui.widget;

import com.daqem.uilib.api.widget.IEditBoxWidget;
import com.daqem.uilib.api.widget.ITextInputWidget;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class EditBoxWidget extends EditBox implements IWidget, ITextInputWidget {

    public EditBoxWidget(Font font, int width, int height, Component message) {
        super(font, width, height, message);
    }

    public EditBoxWidget(Font font, int x, int y, int width, int height, Component message) {
        super(font, x, y, width, height, message);
    }

    public EditBoxWidget(Font font, int x, int y, int width, int height, @Nullable EditBox editBox, Component message) {
        super(font, x, y, width, height, editBox, message);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Had to add this because the text position wasn't updating correctly when set with an initial value
        if (this instanceof IEditBoxWidget editBoxWidget) {
            editBoxWidget.uilib$updateTextPosition();
        }
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
