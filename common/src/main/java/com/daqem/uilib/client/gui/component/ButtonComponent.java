package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class ButtonComponent extends AbstractNineSlicedComponent<ButtonComponent> {

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height, null, null, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text) {
        super(texture, x, y, width, height, text, null, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnClickEvent<ButtonComponent> onClickEvent) {
        super(texture, x, y, width, height, null, onClickEvent, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnHoverEvent<ButtonComponent> onHoverEvent) {
        super(texture, x, y, width, height, null, null, onHoverEvent);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnClickEvent<ButtonComponent> onClickEvent, @Nullable OnHoverEvent<ButtonComponent> onHoverEvent) {
        super(texture, x, y, width, height, null, onClickEvent, onHoverEvent);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<ButtonComponent> onClickEvent) {
        super(texture, x, y, width, height, text, onClickEvent, null);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnHoverEvent<ButtonComponent> onHoverEvent) {
        super(texture, x, y, width, height, text, null, onHoverEvent);
    }

    @SuppressWarnings("unused")
    public ButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<ButtonComponent> onClickEvent, @Nullable OnHoverEvent<ButtonComponent> onHoverEvent) {
        super(texture, x, y, width, height, text, onClickEvent, onHoverEvent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        if (getText() != null) {
            getText().renderBase(guiGraphics, mouseX, mouseY, delta);
        }
    }
}
