package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.api.client.gui.component.io.IIOComponent;
import com.daqem.uilib.client.gui.component.AbstractSpriteComponent;
import com.daqem.uilib.client.gui.text.ScrollingText;
import com.daqem.uilib.client.util.SoundManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

public class CycleButtonComponent<T> extends AbstractSpriteComponent<CycleButtonComponent<T>> implements IIOComponent<CycleButtonComponent<T>> {

    private static final LinkedList<ResourceLocation> DEFAULT_SPRITES = new LinkedList<>(List.of(
            ResourceLocation.withDefaultNamespace("widget/button"),
            ResourceLocation.withDefaultNamespace("widget/button_disabled"),
            ResourceLocation.withDefaultNamespace("widget/button_highlighted")
    ));

    private final List<IOComponentEntry<T>> values;
    private T value;
    private Component prefix;
    private boolean enabled = true;
    private int border = 2;

    public CycleButtonComponent(int x, int y, int width, int height, List<IOComponentEntry<T>> values, T initialValue, Component prefix) {
        this(DEFAULT_SPRITES, x, y, width, height, values, initialValue, prefix);
    }

    public CycleButtonComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height, List<IOComponentEntry<T>> values, T initialValue, Component prefix) {
        super(sprites, x, y, width, height);
        this.values = values;
        this.value = initialValue;
        this.prefix = prefix;

        Font font = Minecraft.getInstance().font;
        ScrollingText text = new ScrollingText(font, getTextFromValue(this.value), border, (height - font.lineHeight + 1) / 2, width - border * 2, font.lineHeight, ScrollingText.Direction.SIDE_TO_SIDE);
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
        else if (isTotalHovered(mouseX, mouseY) || isFocused()) sprite = getSprite(2);

        graphics.blitSprite(sprite, 0, 0, getWidth(), getHeight());
    }

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (!enabled) {
            return false;
        }
        boolean returnValue = false;
        if (isTotalHovered(mouseX, mouseY)) {
            cycleValue();
            setFocused(true);
            returnValue = true;
        }
        if (!returnValue && super.preformOnClickEvent(mouseX, mouseY, button)) {
            returnValue = true;
        }
        if (returnValue) {
            SoundManager.playUIClick();
        }
        return returnValue;
    }

    public Component getTextFromValue(T value) {
        return Component.empty()
                .append(prefix)
                .append(": ")
                .append(values.stream()
                        .filter(entry -> entry.value().equals(value))
                        .map(IOComponentEntry::component)
                        .findFirst()
                        .orElse(Component.empty()));
    }

    public void cycleValue() {
        setValue(getNextValue());
    }

    public T getNextValue() {
        int index = getValueIndex();
        if (index == -1) {
            return null;
        }
        int nextIndex = (index + 1) % values.size();
        return values.get(nextIndex).value();
    }

    public int getValueIndex() {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).value().equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public T getValue() {
        return value;
    }

    public IOComponentEntry<T> getEntry() {
        return values.get(getValueIndex());
    }

    public void setValue(T value) {
        this.value = value;

        if (getText() != null) {
            getText().setText(getTextFromValue(value));
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

    public Component getPrefix() {
        return prefix;
    }

    public void setPrefix(Component prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getStringValue() {
        return getEntry().stringValue();
    }
}
