package com.daqem.uilib.api.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public interface IRenderable<T extends IRenderable<T>> extends IClickable<T>, IHoverable<T>, ICloneable {

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

    /**
     * Called when the screen is opened.
     * Set your background and add your components here.
     */
    void start();

    /**
     * Called every screen tick.
     * NOTE: Do not add components here, use {@link IRenderable#start()} instead.
     *
     * @param graphics the gui graphics
     * @param mouseX the mouse x position
     * @param mouseY the mouse y position
     * @param partialTicks the partial ticks
     */
    void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks);

    /**
     * Called every screen tick.
     * This is called before {@link IRenderable#render(GuiGraphics, int, int, float)} to manage things like color manipulation, position and scale.
     * NOTE: Do not add components here, use {@link IRenderable#start()} instead.
     *
     * @param graphics the gui graphics
     * @param mouseX the mouse x position
     * @param mouseY the mouse y position
     * @param partialTicks the partial ticks
     */
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
    default void preformOnHoverAction() {
        if (getOnHoverAction() != null) {
            getOnHoverAction().onHover(getHoverState());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default void preformOnClickAction() {
        if (getOnClickAction() != null) {
            getOnClickAction().onClick((T) this);
        }
    }
}
