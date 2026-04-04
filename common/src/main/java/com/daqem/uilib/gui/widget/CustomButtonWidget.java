package com.daqem.uilib.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import org.jetbrains.annotations.NotNull;

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

    @Override
    protected void extractContents(@NotNull GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.sprites.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight(), ARGB.white(this.alpha));
        this.extractDefaultLabel(guiGraphics.textRendererForWidget(this, GuiGraphicsExtractor.HoveredTextEffects.NONE));
    }
}
