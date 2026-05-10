package com.daqem.uilib.gui.widget;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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

    @Override
    @Nullable
    public ComponentPath nextFocusPath(@NotNull FocusNavigationEvent focusNavigationEvent) {
        return ContainerEventHandler.super.nextFocusPath(focusNavigationEvent);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.active || !this.visible) {
            return false;
        }
        boolean handled = false;
        if (this.isValidClickButton(button)) {
            if (this.updateScrolling(mouseX, mouseY, button)) {
                handled = true;
            } else {
                Optional<GuiEventListener> optional = this.getChildAt(mouseX, mouseY);
                if (optional.isPresent()) {
                    GuiEventListener guiEventListener = optional.get();
                    if (guiEventListener.mouseClicked(mouseX, mouseY, button)) {
                        this.setFocused(guiEventListener);
                        if (button == 0) {
                            this.setDragging(true);
                        }
                        handled = true;
                    }
                } else {
                    if (this.clicked(mouseX, mouseY)) {
                        this.onClick(mouseX, mouseY);
                        handled = true;
                    }
                }
            }
        }
        return handled;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        boolean handled = super.mouseReleased(mouseX, mouseY, button);
        return ContainerEventHandler.super.mouseReleased(mouseX, mouseY, button) || handled;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling) {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
        boolean handled = ContainerEventHandler.super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        if (!handled) {
            handled = super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
        return handled;
    }

    public boolean isFocused() {
        return super.isFocused();
    }

    public void setFocused(boolean focused) {
        super.setFocused(focused);
    }
}