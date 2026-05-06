package com.daqem.uilib.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
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
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        guiGraphics.blitSprite(this.sprites.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = this.active ? 16777215 : 10526880;
        this.renderScrollingString(guiGraphics, minecraft.font, 2, i | Mth.ceil(this.alpha * 255.0F) << 24);
    }
}
