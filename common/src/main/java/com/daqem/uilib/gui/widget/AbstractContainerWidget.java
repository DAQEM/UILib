package com.daqem.uilib.gui.widget;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractContainerWidget extends AbstractScrollArea implements ContainerEventHandler {
    @Nullable
    private GuiEventListener focused;
    private boolean isDragging;

    public AbstractContainerWidget(int x, int y, int width, int height, Component message, AbstractScrollArea.ScrollbarSettings scrollbarSettings) {
        super(x, y, width, height, message, scrollbarSettings);
    }

    public final boolean isDragging() {
        return this.isDragging;
    }

    public final void setDragging(boolean isDragging) {
        this.isDragging = isDragging;
    }

    @Nullable
    public GuiEventListener getFocused() {
        return this.focused;
    }

    public void setFocused(@Nullable GuiEventListener focused) {
        if (this.focused != null) {
            this.focused.setFocused(false);
        }

        if (focused != null) {
            focused.setFocused(true);
        }

        this.focused = focused;
    }

    @Nullable
    public ComponentPath nextFocusPath(@NotNull FocusNavigationEvent focusNavigationEvent) {
        return super.nextFocusPath(focusNavigationEvent);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    public boolean isFocused() {
        return super.isFocused();
    }

    public void setFocused(boolean focused) {
        super.setFocused(focused);
    }
}
