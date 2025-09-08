package com.daqem.uilib.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

public class CustomButtonWidget extends ButtonWidget {

    private final WidgetSprites sprites;

    public CustomButtonWidget(Component message, WidgetSprites sprites) {
        super(message);
        this.sprites = sprites;
    }

    public CustomButtonWidget(int x, int y, Component message, WidgetSprites sprites) {
        super(x, y, message);
        this.sprites = sprites;
    }

    public CustomButtonWidget(int x, int y, int width, Component message, WidgetSprites sprites) {
        super(x, y, width, message);
        this.sprites = sprites;
    }

    public CustomButtonWidget(int x, int y, int width, int height, Component message, WidgetSprites sprites) {
        super(x, y, width, height, message);
        this.sprites = sprites;
    }

    public CustomButtonWidget(int x, int y, int width, int height, Component message, WidgetSprites sprites, OnPress onPress) {
        super(x, y, width, height, message, onPress);
        this.sprites = sprites;
    }

    public CustomButtonWidget(int x, int y, int width, int height, Component message, WidgetSprites sprites, OnPress onPress, CreateNarration createNarration) {
        super(x, y, width, height, message, onPress, createNarration);
        this.sprites = sprites;
    }

    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.sprites.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight(), ARGB.white(this.alpha));
        int i = ARGB.color(this.alpha, this.active ? -1 : -6250336);
        this.renderString(guiGraphics, minecraft.font, i);
    }
}
