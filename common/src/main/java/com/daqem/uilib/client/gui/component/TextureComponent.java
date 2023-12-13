package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.client.gui.GuiGraphics;

public class TextureComponent extends AbstractComponent<TextureComponent> {

    public TextureComponent(ITexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.blit(getTexture().getTextureLocation(), 0, 0, getWidth(), getHeight(), getTexture().getX(), getTexture().getY(), getTexture().getWidth(), getTexture().getHeight(), getTexture().getFileWidth(), getTexture().getFileHeight());
    }
}
