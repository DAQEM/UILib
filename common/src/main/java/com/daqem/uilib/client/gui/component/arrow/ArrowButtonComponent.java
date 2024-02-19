package com.daqem.uilib.client.gui.component.arrow;

import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import net.minecraft.client.gui.GuiGraphics;

public class ArrowButtonComponent extends ButtonComponent {

    private final ArrowComponent arrow;

    public ArrowButtonComponent(INineSlicedTexture texture, int x, int y, int width, int height, ArrowComponent.Direction direction, int arrowSize, int arrowColor, int arrowThickness) {
        super(texture, x, y, width, height);

        this.arrow = new ArrowComponent(0, 0, arrowSize, direction, arrowColor, arrowThickness);

        this.addChild(arrow);

        this.arrow.center();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.fill(0, 0, getWidth(), getHeight(), 0x00000000);
    }
}
