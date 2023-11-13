package com.daqem.uilib.objects.component;

import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

public class TestComponent extends AbstractComponent<TestComponent> {
    public TestComponent() {
        super(null, 0, 0, 0, 0, null, null, null);
    }

    public TestComponent(int x, int y, int width, int height) {
        super(null, x, y, width, height, null, null, null);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    }
}