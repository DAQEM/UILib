package com.daqem.uilib.gui;

import com.daqem.uilib.api.background.IBackground;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.screen.IScreen;
import com.daqem.uilib.api.screen.IScreenAccessor;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AbstractContainerScreen<T extends AbstractContainerMenu> extends net.minecraft.client.gui.screens.inventory.AbstractContainerScreen<T> implements IScreen {

    private @Nullable IBackground background;
    private final IScreenAccessor screenAccessor = this instanceof IScreenAccessor ? (IScreenAccessor) this : null;

    public AbstractContainerScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        List<? extends GuiEventListener> widgets = super.children();

        if (screenAccessor != null) {
            List<GuiEventListener> combined = new ArrayList<>(widgets);
            for (Renderable renderable : screenAccessor.uilib$getRenderables()) {
                if (renderable instanceof IComponent component) {
                    combined.addAll(component.getAllWidgets());
                }
            }
            return List.copyOf(combined);
        }

        return widgets;
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.background != null) {
            this.background.render(guiGraphics, mouseX, mouseY, partialTick);
        } else {
            super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    }

    @Override
    public @Nullable IBackground getBackground() {
        return this.background;
    }

    @Override
    public void setBackground(@Nullable IBackground background) {
        this.background = background;
    }

    @Override
    public void clearBackground() {
        this.background = null;
    }

    @Override
    public List<IComponent> getComponents() {
        return screenAccessor != null ? screenAccessor.uilib$getRenderables().stream()
                .filter(renderable -> renderable instanceof IComponent)
                .map(renderable -> (IComponent) renderable)
                .toList() : List.of();
    }

    @Override
    public void addComponent(IComponent component) {
        this.addRenderableOnly(component);
    }

    @Override
    public void addComponents(List<? extends IComponent> components) {
        for (IComponent component : components) {
            this.addRenderableOnly(component);
        }
    }

    @Override
    public void removeComponent(IComponent component) {
        if (screenAccessor != null) {
            screenAccessor.uilib$getRenderables().remove(component);
        }
    }

    @Override
    public void removeComponents(List<? extends IComponent> components) {
        if (screenAccessor != null) {
            for (IComponent component : components) {
                screenAccessor.uilib$getRenderables().remove(component);
            }
        }
    }

    @Override
    public void clearComponents() {
        if (screenAccessor != null) {
            screenAccessor.uilib$getRenderables().clear();
        }
    }

    @Override
    public List<IWidget> getWidgets() {
        return children().stream()
                .filter(guiEventListener -> guiEventListener instanceof IWidget)
                .map(guiEventListener -> (IWidget) guiEventListener)
                .toList();
    }

    @Override
    public void addWidget(IWidget widget) {
        super.addRenderableWidget(widget);
    }

    @Override
    public void addWidgets(List<? extends IWidget> widgets) {
        for (IWidget widget : widgets) {
            this.addRenderableWidget(widget);
        }
    }

    @Override
    public void removeWidget(IWidget widget) {
        if (screenAccessor != null) {
            screenAccessor.uilib$removeWidget(widget);
        }
    }

    @Override
    public void removeWidgets(List<? extends IWidget> widgets) {
        if (screenAccessor != null) {
            for (IWidget widget : widgets) {
                screenAccessor.uilib$removeWidget(widget);
            }
        }
    }

    @Override
    public void clearOnlyWidgets() {
        if (screenAccessor != null) {
            screenAccessor.uilib$getRenderables().removeIf(renderable -> renderable instanceof IWidget);
            screenAccessor.uilib$getChildren().removeIf(renderable -> renderable instanceof IWidget);
            screenAccessor.uilib$getNarratables().removeIf(renderable -> renderable instanceof IWidget);
        }
    }

    @Override
    public void clear() {
        if (screenAccessor != null) {
            screenAccessor.uilib$getRenderables().clear();
            screenAccessor.uilib$getChildren().clear();
            screenAccessor.uilib$getNarratables().clear();
        }
    }

    @Override
    protected void init() {
        super.init();

        if (screenAccessor != null) {
            for (Renderable uilib$getRenderable : screenAccessor.uilib$getRenderables()) {
                if (uilib$getRenderable instanceof IComponent component) {
                    component.updateParentPosition(0, 0, this.width, this.height);
                }
                if (uilib$getRenderable instanceof IWidget widget) {
                    widget.uilib$updateParentPosition(0, 0);
                }
            }
        }
    }
}
