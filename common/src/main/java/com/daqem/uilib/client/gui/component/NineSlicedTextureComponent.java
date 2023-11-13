package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class NineSlicedTextureComponent extends AbstractNineSlicedComponent<NineSlicedTextureComponent> {

    public NineSlicedTextureComponent(ITexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<NineSlicedTextureComponent> onClickEvent, @Nullable OnHoverEvent<NineSlicedTextureComponent> onHoverEvent) {
        super(texture, x, y, width, height, text, onClickEvent, onHoverEvent);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
    }
}
