package com.daqem.uilib.objects.background;

import com.daqem.uilib.client.gui.background.AbstractBackground;
import net.minecraft.client.gui.GuiGraphics;

public class TestBackground extends AbstractBackground<TestBackground> {

    public TestBackground(int width, int height) {
        super(width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

    }
}
