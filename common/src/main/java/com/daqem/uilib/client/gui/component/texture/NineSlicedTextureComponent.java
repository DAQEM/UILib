package com.daqem.uilib.client.gui.component.texture;

import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import net.minecraft.client.gui.GuiGraphics;

public class NineSlicedTextureComponent extends AbstractNineSlicedComponent<NineSlicedTextureComponent> {

    public NineSlicedTextureComponent(INineSlicedTexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
    }
}
