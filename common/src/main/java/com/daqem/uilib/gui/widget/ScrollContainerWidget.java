package com.daqem.uilib.gui.widget;

import com.daqem.uilib.api.IParent;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractContainerWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.ScreenDirection;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScrollContainerWidget extends AbstractContainerWidget implements IWidget, IParent {

    private final List<IComponent> components = new ArrayList<>();
    private final int contentSpacing;

    public ScrollContainerWidget(int width, int height, int contentSpacing) {
        super(0, 0, width, height, Component.empty());
        this.contentSpacing = contentSpacing;
    }

    public ScrollContainerWidget(int width, int height) {
        this(width, height, 0);
    }

    @Override
    protected int contentHeight() {
        if (components.isEmpty()) {
            return 0;
        }
        return this.components.stream()
                .mapToInt(IComponent::getHeight)
                .sum() + (getContentSpacing() * (this.components.size() - 1));
    }

    @Override
    protected double scrollRate() {
        return 10.0;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.enableScissor(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height);

        int currentY = this.uilib$getParentY() - this.getY() - (int) this.scrollAmount();
        for (int i = 0; i < this.components.size(); i++) {
            IComponent component = this.components.get(i);
            component.setY(currentY);
            component.renderBase(guiGraphics, mouseX, mouseY, partialTick, this.width, this.height);
            currentY += component.getHeight();
            if (i < this.components.size() - 1) {
                currentY += getContentSpacing();
            }
        }

        guiGraphics.disableScissor();
        this.renderScrollbar(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    @Override
    public @NotNull ScreenRectangle getBorderForArrowNavigation(ScreenDirection direction) {
        return new ScreenRectangle(this.getX(), this.getY(), this.width, this.contentHeight());
    }

    @Override
    public void setFocused(@Nullable GuiEventListener focused) {
        super.setFocused(focused);
        if (focused != null && Minecraft.getInstance().getLastInputType().isKeyboard()) {
            ScreenRectangle screenRectangle = this.getRectangle();
            ScreenRectangle screenRectangle2 = focused.getRectangle();
            int i = screenRectangle2.top() - screenRectangle.top();
            int j = screenRectangle2.bottom() - screenRectangle.bottom();
            if (i < 0) {
                this.setScrollAmount(this.scrollAmount() + i - 14.0);
            } else if (j > 0) {
                this.setScrollAmount(this.scrollAmount() + j + 14.0);
            }
        }
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return getWidgets();
    }

    @Override
    public @NotNull Collection<? extends NarratableEntry> getNarratables() {
        return getWidgets();
    }

    @Override
    public List<IComponent> getComponents() {
        return this.components;
    }

    @Override
    public void addComponent(IComponent component) {
        this.components.add(component);
    }

    @Override
    public void addComponents(List<? extends IComponent> components) {
        this.components.addAll(components);
    }

    @Override
    public void removeComponent(IComponent component) {
        this.components.remove(component);
    }

    @Override
    public void removeComponents(List<? extends IComponent> components) {
        this.components.removeAll(components);
    }

    @Override
    public void clearComponents() {
        this.components.clear();
    }

    @Override
    public void clear() {
        clearComponents();
        clearOnlyWidgets();
    }

    @Override
    public List<IWidget> getWidgets() {
        return this.components.stream()
                .flatMap(component -> component.getAllWidgets().stream())
                .toList();
    }

    @Override
    public void addWidget(IWidget widget) {
        throw new UnsupportedOperationException("Cannot add a widget directly to ScrollContainerWidget. Add it to a component instead.");
    }

    @Override
    public void addWidgets(List<? extends IWidget> widgets) {
        throw new UnsupportedOperationException("Cannot add widgets directly to ScrollContainerWidget. Add them to a component instead.");
    }

    @Override
    public void removeWidget(IWidget widget) {
        throw new UnsupportedOperationException("Cannot remove a widget directly from ScrollContainerWidget. Remove it from a component instead.");
    }

    @Override
    public void removeWidgets(List<? extends IWidget> widgets) {
        throw new UnsupportedOperationException("Cannot remove widgets directly from ScrollContainerWidget. Remove them from a component instead.");
    }

    @Override
    public void clearOnlyWidgets() {
        throw new UnsupportedOperationException("Cannot clear widgets directly from ScrollContainerWidget. Clear them from components instead.");
    }

    public int getContentSpacing() {
        return contentSpacing;
    }
}