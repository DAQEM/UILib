package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractNineSlicedComponent<T extends AbstractNineSlicedComponent<T>> extends AbstractComponent<T> {

    public AbstractNineSlicedComponent(ITexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        INineSlicedTexture texture = (INineSlicedTexture) getTexture();
        if (texture == null) return;
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
