package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.client.gui.component.AbstractSpriteComponent;
import com.daqem.uilib.client.gui.text.ScrollingText;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

public class ButtonComponent extends AbstractSpriteComponent<ButtonComponent> {

    private static final LinkedList<ResourceLocation> DEFAULT_SPRITES = new LinkedList<>(List.of(
            ResourceLocation.withDefaultNamespace("widget/button"),
            ResourceLocation.withDefaultNamespace("widget/button_disabled"),
            ResourceLocation.withDefaultNamespace("widget/button_highlighted")
    ));

    private boolean enabled = true;
    private int border = 2;

    public ButtonComponent(int x, int y, int width, int height, Component component) {
        this(x, y, width, height, component, null);
    }

    public ButtonComponent(int x, int y, int width, int height, Component component, OnClickEvent<ButtonComponent> onClickEvent) {
        this(DEFAULT_SPRITES, x, y, width, height, component, onClickEvent);
    }

    public ButtonComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height, Component component) {
        this(sprites, x, y, width, height, component, null);
    }

    public ButtonComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height, Component component, OnClickEvent<ButtonComponent> onClickEvent) {
        super(sprites, x, y, width, height);

        setOnClickEvent(onClickEvent);

        Font font = Minecraft.getInstance().font;
        ScrollingText text = new ScrollingText(font, component, border, (height - font.lineHeight + 1) / 2, width - border * 2, font.lineHeight, ScrollingText.Direction.SIDE_TO_SIDE);
        text.setDefaultCentered(true);
        text.setShadow(true);
        this.setText(text);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        ResourceLocation sprite = getSprite(0);

        if (!enabled) sprite = getSprite(1);
        else if (isTotalHovered(mouseX, mouseY)) sprite = getSprite(2);

        graphics.blitSprite(sprite, 0, 0, getWidth(), getHeight());

        if (getText() != null) {
            int textColor = this.enabled ? 0xFFFFFF : 0xA0A0A0;
            getText().setTextColor(textColor);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
}