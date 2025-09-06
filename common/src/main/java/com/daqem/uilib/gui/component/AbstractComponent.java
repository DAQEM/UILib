package com.daqem.uilib.gui.component;

import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.widget.IWidget;
import com.daqem.uilib.api.widget.IWidgetsParent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractComponent implements IComponent {

    private boolean isCenteredHorizontally;
    private boolean isCenteredVertically;

    private int x;
    private int y;
    private int parentX;
    private int parentY;
    private int width;
    private int height;

    private final List<IComponent> components = new ArrayList<>();
    private final List<IWidget> widgets = new ArrayList<>();

    private boolean renderBeforeParent = false;
    private boolean renderDebugBorder = false;

    public AbstractComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isCenteredHorizontally() {
        return isCenteredHorizontally;
    }

    @Override
    public boolean isCenteredVertically() {
        return isCenteredVertically;
    }

    @Override
    public boolean isCentered() {
        return isCenteredHorizontally && isCenteredVertically;
    }

    @Override
    public void centerHorizontally() {
        this.isCenteredHorizontally = true;
    }

    @Override
    public void centerVertically() {
        this.isCenteredVertically = true;
    }

    @Override
    public void center() {
        centerHorizontally();
        centerVertically();
    }

    @Override
    public void decenterHorizontally() {
        this.isCenteredHorizontally = false;
    }

    @Override
    public void decenterVertically() {
        this.isCenteredVertically = false;
    }

    @Override
    public void decenter() {
        decenterHorizontally();
        decenterVertically();
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.positionUpdated();
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.positionUpdated();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.positionUpdated();
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        this.positionUpdated();
    }

    @Override
    public int getParentX() {
        return parentX;
    }

    @Override
    public int getParentY() {
        return parentY;
    }

    @Override
    public int getTotalX() {
        return getParentX() + getX();
    }

    @Override
    public int getTotalY() {
        return getParentY() + getY();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void visitWidgets(Consumer<AbstractWidget> consumer) {
        this.widgets.stream()
                .filter(widget -> widget instanceof AbstractWidget)
                .forEach(widget -> consumer.accept((AbstractWidget) widget));
        this.components.forEach(component -> component.visitWidgets(consumer));
    }

    @Override
    public List<IComponent> getComponents() {
        return components;
    }

    @Override
    public List<IWidget> getWidgets() {
        return widgets;
    }

    @Override
    public List<IWidget> getAllWidgets() {
        List<IWidget> allWidgets = new ArrayList<>(widgets);
        for (IComponent component : components) {
            allWidgets.addAll(component.getAllWidgets());
        }
        return allWidgets;
    }

    @Override
    public void getAllWidgetsMap(Map<IWidget, IComponent> map) {
        for (IWidget widget : widgets) {
            map.put(widget, this);
        }
        for (IComponent component : components) {
            component.getAllWidgetsMap(map);
        }
    }

    @Override
    public boolean isRenderBeforeParent() {
        return renderBeforeParent;
    }

    @Override
    public void setRenderBeforeParent(boolean renderBeforeParent) {
        this.renderBeforeParent = renderBeforeParent;
    }

    @Override
    public boolean isRenderDebugBorder() {
        return renderDebugBorder;
    }

    @Override
    public void setRenderDebugBorder(boolean renderDebugBorder) {
        this.renderDebugBorder = renderDebugBorder;
    }

    @Override
    public void positionUpdated() {
        for (IComponent component : components) {
            component.updateParentPosition(getTotalX(), getTotalY(), getWidth(), getHeight());
        }
        for (IWidget widget : widgets) {
            widget.uilib$updateParentPosition(getTotalX(), getTotalY());
        }
    }

    @Override
    public void updateParentPosition(int parentX, int parentY, int parentWidth, int parentHeight) {
        this.parentX = parentX;
        this.parentY = parentY;

        if (isCenteredHorizontally) {
            this.x = (parentWidth - this.width) / 2;
        }

        if (isCenteredVertically) {
            this.y = (parentHeight - this.height) / 2;
        }

        for (IComponent component : components) {
            component.updateParentPosition(getTotalX(), getTotalY(), width, height);
        }

        for (IWidget widget : widgets) {
            widget.uilib$updateParentPosition(getTotalX(), getTotalY());
        }
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
    public void addWidget(IWidget widget) {
        this.widgets.add(widget);
    }

    @Override
    public void addWidgets(List<? extends IWidget> widgets) {
        this.widgets.addAll(widgets);
    }

    @Override
    public void removeWidget(IWidget widget) {
        this.widgets.remove(widget);
    }

    @Override
    public void removeWidgets(List<? extends IWidget> widgets) {
        this.widgets.removeAll(widgets);
    }

    @Override
    public void clearOnlyWidgets() {
        this.widgets.clear();
    }

    @Override
    public void clear() {
        clearComponents();
        clearOnlyWidgets();
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        for (IComponent component : components.stream().filter(IComponent::isRenderBeforeParent).toList()) {
            component.renderBase(guiGraphics, mouseX, mouseY, partialTick, width, height);
        }

        this.render(guiGraphics, mouseX, mouseY, partialTick, parentWidth, parentHeight);

        if (isRenderDebugBorder()) {
            guiGraphics.hLine(getTotalX(), getTotalX() + getWidth() - 1, getTotalY(), 0xAAFF0000);
            guiGraphics.vLine(getTotalX() + getWidth() - 1, getTotalY(), getTotalY() + getHeight() - 1, 0xAAFF0000);
            guiGraphics.hLine(getTotalX(), getTotalX() + getWidth() - 1, getTotalY() + getHeight() - 1, 0xAAFF0000);
            guiGraphics.vLine(getTotalX(), getTotalY(), getTotalY() + getHeight() - 1, 0xAAFF0000);
        }

        for (IComponent component : components.stream().filter(c -> !c.isRenderBeforeParent()).toList()) {
            component.renderBase(guiGraphics, mouseX, mouseY, partialTick, width, height);
        }

        for (IWidget widget : widgets) {
            widget.render(guiGraphics, mouseX, mouseY, partialTick);
        }

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen instanceof Screen screen) {
            renderBase(guiGraphics, mouseX, mouseY, partialTick, screen.width, screen.height);
        }
    }
}
