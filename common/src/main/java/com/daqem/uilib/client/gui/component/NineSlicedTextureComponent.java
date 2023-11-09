package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.component.action.OnClickAction;
import com.daqem.uilib.api.client.gui.component.action.OnHoverAction;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class NineSlicedTextureComponent extends AbstractComponent<NineSlicedTextureComponent> {

    public NineSlicedTextureComponent(ITexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickAction<NineSlicedTextureComponent> onClickAction, @Nullable OnHoverAction<NineSlicedTextureComponent> onHoverAction) {
        super(texture, x, y, width, height, text, onClickAction, onHoverAction);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        INineSlicedTexture texture = (INineSlicedTexture) getTexture();
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
