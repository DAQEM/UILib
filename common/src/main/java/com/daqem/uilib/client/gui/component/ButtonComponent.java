package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.component.action.OnClickAction;
import com.daqem.uilib.api.client.gui.component.action.OnHoverAction;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class ButtonComponent extends AbstractComponent<ButtonComponent> {

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height, null, null, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text) {
        super(texture, x, y, width, height, text, null, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnClickAction<ButtonComponent> onClickAction) {
        super(texture, x, y, width, height, null, onClickAction, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnHoverAction<ButtonComponent> onHoverAction) {
        super(texture, x, y, width, height, null, null, onHoverAction);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnClickAction<ButtonComponent> onClickAction, @Nullable OnHoverAction<ButtonComponent> onHoverAction) {
        super(texture, x, y, width, height, null, onClickAction, onHoverAction);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickAction<ButtonComponent> onClickAction) {
        super(texture, x, y, width, height, text, onClickAction, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnHoverAction<ButtonComponent> onHoverAction) {
        super(texture, x, y, width, height, text, null, onHoverAction);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickAction<ButtonComponent> onClickAction, @Nullable OnHoverAction<ButtonComponent> onHoverAction) {
        super(texture, x, y, width, height, text, onClickAction, onHoverAction);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        INineSlicedTexture texture = (INineSlicedTexture) getTexture();
        guiGraphics.blitNineSliced(
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
        if (getText() != null) {
            getText().renderBase(guiGraphics, mouseX, mouseY, partialTicks);
        }
    }
}
