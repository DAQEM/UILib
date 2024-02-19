package com.daqem.uilib.client.gui.component.texture;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

public class RepeatingTextureComponent extends AbstractComponent<RepeatingTextureComponent> {

    public RepeatingTextureComponent(ITexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.blit(getTexture().getTextureLocation(), 0, 0, 0, getTexture().getX(), getTexture().getY(), getWidth(), getHeight(), getTexture().getWidth(), getTexture().getHeight());
    }
}
