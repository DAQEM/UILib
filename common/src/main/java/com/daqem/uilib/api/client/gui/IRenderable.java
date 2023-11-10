package com.daqem.uilib.api.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

public interface IRenderable<T extends IRenderable<T>> extends IClickable<T>, IHoverable<T>, ICloneable {

    @Nullable Screen getScreen();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    boolean isVisible();

    void setScreen(@Nullable Screen screen);
    void setX(int x);
    void setY(int y);
    void setWidth(int width);
    void setHeight(int height);
    void setVisible(boolean visible);

    void startRenderable();

    void resizeScreenRepositionRenderable(int width, int height);

    void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks);
    void renderBase(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks);

    @Override
    default boolean isClicked(double mouseX, double mouseY) {
        return isHovered(mouseX, mouseY);
    }

    @Override
    default boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight();
    }

    @Override
    default void preformOnHoverAction(double mouseX, double mouseY) {
        if (getOnHoverAction() != null) {
            getOnHoverAction().onHover(getHoverState(), getScreen(), mouseX, mouseY);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default void preformOnClickAction(double mouseX, double mouseY) {
        if (getOnClickAction() != null) {
            getOnClickAction().onClick((T) this, getScreen(), mouseX, mouseY);
        }
    }

}
