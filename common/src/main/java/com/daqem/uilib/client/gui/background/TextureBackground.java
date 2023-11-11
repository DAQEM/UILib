package com.daqem.uilib.client.gui.background;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.client.gui.GuiGraphics;

public class TextureBackground extends AbstractBackground<TextureBackground> {

    private ITexture texture;

    public TextureBackground(int width, int height, ITexture texture) {
        super(width, height);
        this.texture = texture;
    }

    public TextureBackground(int x, int y, int width, int height, ITexture texture) {
        super(x, y, width, height);
        this.texture = texture;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        guiGraphics.blit(texture.getTextureLocation(), 0, 0, 0, 0.0f, 0.0f, getWidth(), getHeight(), texture.getWidth(), texture.getHeight());
    }

    public ITexture getTexture() {
        return texture;
    }

    public void setTexture(ITexture texture) {
        this.texture = texture;
    }
}
