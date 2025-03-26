package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import net.minecraft.client.gui.GuiGraphics;

public class ButtonComponent extends AbstractNineSlicedComponent<ButtonComponent> {

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, int color) {
        super.render(guiGraphics, mouseX, mouseY, delta, color);
    }
}
