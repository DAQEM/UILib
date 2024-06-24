package com.daqem.uilib.client.gui.background;

import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
import net.minecraft.client.gui.GuiGraphics;

public class NineSlicedBackground extends TextureBackground {

    public NineSlicedBackground(int width, int height, NineSlicedTexture texture) {
        super(width, height, texture);
    }

    public NineSlicedBackground(int x, int y, int width, int height, NineSlicedTexture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        NineSlicedTexture texture = (NineSlicedTexture) getTexture();
        graphics.blitNineSliced(
                texture.getTextureLocation(),
                0,
                0,
                getWidth(),
                getHeight(),
                texture.getLeftSliceWidth(),
                texture.getTopSliceHeight(),
                texture.getRightSliceWidth(),
                texture.getBottomSliceHeight(),
                texture.getWidth(),
                texture.getHeight(),
                texture.getX(),
                texture.getY()
        );
    }
}
