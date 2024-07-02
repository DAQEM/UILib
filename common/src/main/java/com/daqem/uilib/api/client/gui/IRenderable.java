package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.event.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public interface IRenderable<T extends IRenderable<T>> extends IClickable<T>, IHoverable<T>, IDraggable<T>, IScrollable<T>, IKeyPressable<T>, ICharTypable<T>, IMouseReleasable<T>, ICloneable {

    int getX();
    int getY();
    int getTotalX();
    int getTotalY();
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
        return isTotalHovered(mouseX, mouseY);
    }

    @Override
    default boolean isDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return isTotalHovered(mouseX, mouseY);
    }

    @Override
    default boolean isScrolled(double mouseX, double mouseY, double amountX, double amountY) {
        return isTotalHovered(mouseX, mouseY);
    }

    @Override
    default void preformOnHoverEvent(double mouseX, double mouseY, float delta) {
        if (getOnHoverEvent() != null) {
            if (this.isTotalHovered(mouseX, mouseY)) {
                getOnHoverEvent().onHover(getHoverState(), Minecraft.getInstance().screen, mouseX, mouseY, delta);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (getOnClickEvent() != null) {
            if (this.isTotalHovered(mouseX, mouseY)) {
                return getOnClickEvent().onClick((T) this, Minecraft.getInstance().screen, mouseX, mouseY, button);
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    default boolean preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (getOnDragEvent() != null) {
            if (this.isTotalHovered(mouseX, mouseY)) {
                return getOnDragEvent().onDrag((T) this, Minecraft.getInstance().screen, mouseX, mouseY, button, dragX, dragY);
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    default boolean preformOnScrollEvent(double mouseX, double mouseY, double amountX, double amountY) {
        if (getOnScrollEvent() != null) {
            if (this.isTotalHovered(mouseX, mouseY)) {
                return getOnScrollEvent().onScroll((T) this, Minecraft.getInstance().screen, mouseX, mouseY, amountX, amountY);
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    default boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        if (getOnKeyPressedEvent() != null) {
            return getOnKeyPressedEvent().onKeyPressed((T) this, Minecraft.getInstance().screen, keyCode, scanCode, modifiers);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    default boolean preformOnCharTypedEvent(char typedChar, int modifiers) {
        if (getOnCharTypedEvent() != null) {
            return getOnCharTypedEvent().onCharTyped((T) this, Minecraft.getInstance().screen, typedChar, modifiers);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    default boolean preformOnMouseReleaseEvent(double mouseX, double mouseY, int button) {
        if (getOnMouseReleaseEvent() != null) {
            if (this.isTotalHovered(mouseX, mouseY)) {
                return getOnMouseReleaseEvent().onMouseRelease((T) this, Minecraft.getInstance().screen, mouseX, mouseY, button);
            }
        }
        return false;
    }
}
