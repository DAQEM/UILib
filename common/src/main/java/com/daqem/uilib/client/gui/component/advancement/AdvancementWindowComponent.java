package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.client.gui.component.texture.NineSlicedTextureComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;

public class AdvancementWindowComponent extends NineSlicedTextureComponent {

    public AdvancementWindowComponent(int x, int y, int width, int height) {
        super(Textures.Advancement.ADVANCEMENT_WINDOW, x, y, width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        RenderSystem.enableBlend();
        super.render(graphics, mouseX, mouseY, delta);
        RenderSystem.disableBlend();
    }
}
