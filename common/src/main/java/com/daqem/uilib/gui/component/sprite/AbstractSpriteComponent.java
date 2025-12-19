package com.daqem.uilib.gui.component.sprite;

import com.daqem.uilib.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public abstract class AbstractSpriteComponent extends AbstractComponent {

    private final Identifier spriteLocation;

    public AbstractSpriteComponent(int x, int y, int width, int height, Identifier spriteLocation) {
        super(x, y, width, height);
        this.spriteLocation = spriteLocation;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        guiGraphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                spriteLocation,
                getTotalX(),
                getTotalY(),
                getWidth(),
                getHeight()
        );
    }
}
