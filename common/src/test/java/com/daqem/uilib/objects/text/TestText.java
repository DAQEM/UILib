package com.daqem.uilib.objects.text;

import com.daqem.uilib.client.gui.text.AbstractText;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TestText extends AbstractText<TestText> {

    public TestText() {
        super(null, null, 0, 0, 0, 0);
    }

    public TestText(Font font, Component text, int x, int y) {
        super(font, text, x, y);
    }

    public TestText(Font font, Component text, int x, int y, int width, int height) {
        super(font, text, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

    }
}
