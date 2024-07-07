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
import net.minecraft.util.Mth;

import java.util.*;

public class SliderComponent<T> extends AbstractSpriteComponent<SliderComponent<T>> implements IIOComponent<SliderComponent<T>> {

    private final boolean initialized;
    private double doubleValue = 0;
    private final List<IOComponentEntry<T>> values;
    private T value;
    private Component prefix;
    private int border = 2;

    private static final LinkedList<ResourceLocation> DEFAULT_SPRITES = new LinkedList<>(List.of(
            ResourceLocation.withDefaultNamespace("widget/slider"),
            ResourceLocation.withDefaultNamespace("widget/slider_handle"),
            ResourceLocation.withDefaultNamespace("widget/slider_handle_highlighted")
    ));

    public SliderComponent(int x, int y, int width, int height, List<IOComponentEntry<T>> values, T initialValue, Component prefix) {
        this(DEFAULT_SPRITES, x, y, width, height, values, initialValue, prefix);
    }

    public SliderComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height, List<IOComponentEntry<T>> values, T initialValue, Component prefix) {
        super(sprites, x, y, width, height);
        this.values = values;
        this.prefix = prefix;
        setValue(initialValue);

        Font font = Minecraft.getInstance().font;
        ScrollingText text = new ScrollingText(font, Component.empty(), border, (height - font.lineHeight + 1) / 2, width - border * 2, font.lineHeight, ScrollingText.Direction.SIDE_TO_SIDE);
        text.setDefaultCentered(true);
        this.setText(text);

        this.setDoubleValue(Mth.clamp(doubleValue, 0.0, 1.0));
        this.initialized = true;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        ResourceLocation handleSprite = getSprite(1);
        if (isTotalHovered(mouseX, mouseY) || isFocused()) {
            handleSprite = getSprite(2);
        }
        graphics.blitSprite(getSprite(0), 0, 0, this.getWidth(), this.getHeight());
        graphics.blitSprite(handleSprite, (int) (this.doubleValue * (double) (getWidth() - 8)), 0, 8, this.getHeight());
    }

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (isTotalHovered(mouseX, mouseY)) setValueFromMouse(mouseX);
        return super.preformOnClickEvent(mouseX, mouseY, button);
    }

    @Override
    public boolean preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (isTotalHovered(mouseX, mouseY)) setValueFromMouse(mouseX);
        return super.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean preformOnMouseReleaseEvent(double mouseX, double mouseY, int button) {
        if (isTotalHovered(mouseX, mouseY)) SoundManager.playUIClick();
        return super.preformOnMouseReleaseEvent(mouseX, mouseY, button);
    }

    private void setValueFromMouse(double d) {
        this.setDoubleValue((d - (double) (this.getTotalX() + 4)) / (double) (getWidth() - 8));
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        double oldValue = this.doubleValue;
        this.doubleValue = Mth.clamp(doubleValue, 0.0, 1.0);
        if (oldValue != this.doubleValue) {
            this.applyValue();
        }
        if (this.getText() != null) {
            this.getText().setText(updateMessage());
        }
        if (initialized) {
            setFocused(true);
        }
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.doubleValue = (double) getValueIndex() / (values.size() - 1);
        if (this.getText() != null) {
            this.getText().setText(updateMessage());
        }
    }

    public IOComponentEntry<T> getEntry() {
        return values.stream()
                .filter(entry -> entry.value().equals(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getStringValue() {
        return getEntry().stringValue();
    }

    public void applyValue() {
        int size = values.size();
        int index = (int) Math.round(doubleValue * (size - 1));
        value = getValueAt(index);
    }

    public int getValueIndex() {
        int index = 0;
        for (IOComponentEntry<T> t : values) {
            if (t.value().equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public T getValueAt(int index) {
        return values.get(index).value();
    }

    public Component updateMessage() {
        return !prefix.getString().isEmpty() ? Component.empty()
                .append(prefix)
                .append(": ")
                .append(getEntry().component()) : getEntry().component();
    }
}
