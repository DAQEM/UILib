package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.event.IClickable;
import com.daqem.uilib.api.client.gui.event.IDraggable;
import com.daqem.uilib.api.client.gui.event.IHoverable;
import com.daqem.uilib.api.client.gui.event.IScrollable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.vehicle.Minecart;
import org.jetbrains.annotations.Nullable;

public interface IRenderable<T extends IRenderable<T>> extends IClickable<T>, IHoverable<T>, IDraggable<T>, IScrollable<T>, ICloneable {

    int getX();
    int getY();
    int getWidth();
    int getHeight();
    boolean isVisible();

    void setX(int x);
    void setY(int y);
    void setWidth(int width);
    void setHeight(int height);
    void setVisible(boolean visible);

    void startRenderable();

    void resizeScreenRepositionRenderable(int width, int height);

    void render(GuiGraphics graphics, int mouseX, int mouseY, float delta);
    void renderBase(GuiGraphics graphics, int mouseX, int mouseY, float delta);

    @Override
    default boolean isClicked(double mouseX, double mouseY, int button) {
        return isHovered(mouseX, mouseY);
    }

    @Override
    default boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight();
    }

    @Override
    default boolean isDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return isHovered(mouseX, mouseY);
    }

    @Override
    default boolean isScrolled(double mouseX, double mouseY, double delta) {
        return isHovered(mouseX, mouseY);
    }

    @Override
    default void preformOnHoverEvent(double mouseX, double mouseY, float delta) {
        if (getOnHoverEvent() != null) {
            if (this.isHovered(mouseX, mouseY)) {
                getOnHoverEvent().onHover(getHoverState(), Minecraft.getInstance().screen, mouseX, mouseY, delta);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default void preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (getOnClickEvent() != null) {
            if (this.isHovered(mouseX, mouseY)) {
                getOnClickEvent().onClick((T) this, Minecraft.getInstance().screen, mouseX, mouseY, button);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default void preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (getOnDragEvent() != null) {
            if (this.isHovered(mouseX, mouseY)) {
                getOnDragEvent().onDrag((T) this, Minecraft.getInstance().screen, mouseX, mouseY, button, dragX, dragY);
            }
        }
    }

    @SuppressWarnings("unchecked")
    default void preformOnScrollEvent(double mouseX, double mouseY, double delta) {
        if (getOnScrollEvent() != null) {
            if (this.isHovered(mouseX, mouseY)) {
                getOnScrollEvent().onScroll((T) this, Minecraft.getInstance().screen, mouseX, mouseY, delta);
            }
        }
    }
}
