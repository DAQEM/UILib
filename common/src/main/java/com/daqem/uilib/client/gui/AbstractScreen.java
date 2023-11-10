package com.daqem.uilib.client.gui;

import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.IScreen;
import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.client.gui.background.Backgrounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class AbstractScreen extends Screen implements IScreen {

    private final List<IComponent<?>> components = new ArrayList<>();
    private @Nullable IBackground<?> background = Backgrounds.getDefaultBackground(this);

    /**
     * UI Lib screen constructor with title component
     * Please use this constructor if you want the title to be translatable.
     * See {@link Component#translatable(String, Object...)} for more information.
     * @param title the title component
     */
    protected AbstractScreen(Component title) {
        super(title);
    }

    /**
     * UI Lib screen constructor with title string
     * Please use this constructor if you want the title to be literal and not translatable.
     * @param title the title string
     */
    @SuppressWarnings("unused")
    protected AbstractScreen(String title) {
        super(Component.literal(title));
    }

    @Override
    protected void init() {
        super.init();
        if (!initialized) {
            this.startScreen();
            if (getBackground() != null) {
                this.getBackground().startRenderable();
            }
            this.getComponents().forEach(IRenderable::startRenderable);
        } else {
            this.onResizeScreenRepositionComponents(this.width, this.height);
            if (getBackground() != null) {
                this.getBackground().resizeScreenRepositionRenderable(this.width, this.height);
            }
            this.getComponents().forEach(component -> component.resizeScreenRepositionRenderable(this.width, this.height));
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        //check if the mouse is hovering over a component
        this.checkHovering(mouseX, mouseY);
        //Render background
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        //render Minecraft widgets
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        //render UI Lib components
        this.renderComponents(guiGraphics, mouseX, mouseY, partialTicks);
        //render everything else
        this.onTickScreen(guiGraphics, mouseX, mouseY, partialTicks);
    }

    private void checkHovering(int mouseX, int mouseY) {
        for (IComponent<?> component : components) {
            if (component.isHovered(mouseX, mouseY)) {
                component.preformOnHoverAction(mouseX, mouseY);
            }
        }
    }

    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (background != null) {
            background.renderBase(guiGraphics, mouseX, mouseY, partialTicks);
        }
    }

    private void renderComponents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        for (IComponent<?> component : components) {
            component.renderBase(guiGraphics, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public List<IComponent<?>> getComponents() {
        return components;
    }

    @Override
    public void addComponent(IComponent<?> component) {
        components.add(component);
        component.setScreen(this);
    }

    @Override
    public void addComponents(IComponent<?>... components) {
        this.components.addAll(List.of(components));
        this.components.forEach(component -> component.setScreen(this));
    }

    @Override
    public void removeComponent(IComponent<?> component) {
        components.remove(component);
        component.setScreen(null);
    }

    @Override
    public @Nullable IBackground<?> getBackground() {
        return background;
    }

    @Override
    public void setBackground(@Nullable IBackground<?> background) {
        this.background = background;
        if (background != null) {
            background.setScreen(this);
        }
    }

    @Override
    public void onResizeScreenRepositionComponents(int width, int height) {
        repositionComponents(components, width, height);
    }

    private void repositionComponents(List<IComponent<?>> components, int width, int height) {
        for (IComponent<?> component : components) {
            centerComponent(width, height, component);
            repositionComponents(component.getChildren(), width - component.getX(), height - component.getY());
        }
    }

    private static void centerComponent(int width, int height, IComponent<?> component) {
        if (component.isCenteredHorizontally()) {
            component.setX((width / 2) - (component.getWidth() / 2));
        }
        if (component.isCenteredVertically()) {
            component.setY((height / 2) - (component.getHeight() / 2));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int clickType) {
        handleClickAction(components, mouseX, mouseY);
        return super.mouseClicked(mouseX, mouseY, clickType);
    }

    private void handleClickAction(List<IComponent<?>> components, double mouseX, double mouseY) {
        for (IComponent<?> component : components) {
            if (component.isHovered(mouseX, mouseY)) {
                component.preformOnClickAction(mouseX, mouseY);
            }
            handleClickAction(component.getChildren(), mouseX - component.getX(), mouseY - component.getY());
        }
    }
}
