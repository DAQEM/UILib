package com.daqem.uilib.client.gui;

import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.IScreen;
import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.client.gui.background.Backgrounds;
import net.minecraft.client.gui.Font;
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
    private @Nullable IComponent<?> focusedComponent;
    private @Nullable IBackground<?> background = Backgrounds.getDefaultBackground(this.getWidth(), this.getHeight());
    private boolean isPauseScreen = false;

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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        //check if the mouse is hovering over a component
        this.checkHovering(mouseX, mouseY, delta);
        //Render background
        this.renderBackground(guiGraphics, mouseX, mouseY, delta);
        //render UI Lib components
        this.renderComponents(guiGraphics, mouseX, mouseY, delta);
        //render screen tick
        this.onTickScreen(guiGraphics, mouseX, mouseY, delta);
        //render Minecraft widgets
        super.render(guiGraphics, mouseX, mouseY, delta);
        //render tooltip
        this.renderTooltips(guiGraphics, mouseX, mouseY, delta);
    }

    private void checkHovering(int mouseX, int mouseY, float delta) {
        handleHoverEvent(components, mouseX, mouseY, delta);
    }

    private void handleHoverEvent(List<IComponent<?>> components, int mouseX, int mouseY, float delta) {
        for (IComponent<?> component : components) {
            handleHoverEvent(component.getChildren(), mouseX, mouseY, delta);
            component.preformOnHoverEvent(mouseX, mouseY, delta);
        }
    }

    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (background != null) {
            background.renderBase(guiGraphics, mouseX, mouseY, delta);
        }
    }

    public void renderComponents(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        for (IComponent<?> component : new ArrayList<>(components)) {
            component.renderBase(guiGraphics, mouseX, mouseY, delta);
        }
    }

    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        for (IComponent<?> component : new ArrayList<>(components)) {
            component.renderTooltipsBase(guiGraphics, mouseX, mouseY, delta);
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
    public Font getFont() {
        return this.font;
    }

    @Override
    public List<IComponent<?>> getComponents() {
        return components;
    }

    @Override
    public @Nullable IComponent<?> getFocusedComponent() {
        return focusedComponent;
    }

    @Override
    public void setFocusedComponent(@Nullable IComponent<?> focusedComponent) {
        this.focusedComponent = focusedComponent;
    }

    @Override
    public void addComponent(IComponent<?> component) {
        components.add(component);
    }

    @Override
    public void addComponents(IComponent<?>... components) {
        this.components.addAll(List.of(components));
    }

    @Override
    public void removeComponent(IComponent<?> component) {
        components.remove(component);
    }

    @Override
    public @Nullable IBackground<?> getBackground() {
        return background;
    }

    @Override
    public void setBackground(@Nullable IBackground<?> background) {
        this.background = background;
    }

    @Override
    public void onResizeScreenRepositionComponents(int width, int height) {
        repositionComponents(components, width, height);
    }

    private void repositionComponents(List<IComponent<?>> components, int width, int height) {
        for (IComponent<?> component : new ArrayList<>(components)) {
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
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (handleClickEvent(components, mouseX, mouseY, button)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean handleClickEvent(List<IComponent<?>> components, double mouseX, double mouseY, int button) {
        for (IComponent<?> component : new ArrayList<>(components)) {
            if (handleClickEvent(component.getChildren(), mouseX, mouseY, button)) {
                return true;
            }
            if (component.preformOnClickEvent(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (handleDragEvent(components, mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    private boolean handleDragEvent(List<IComponent<?>> components, double mouseX, double mouseY, int button, double dragX, double dragY) {
        for (IComponent<?> component : new ArrayList<>(components)) {
            if (handleDragEvent(component.getChildren(), mouseX, mouseY, button, dragX, dragY)) {
                return true;
            }
            if (component.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amountX, double amountY) {
        if (handleScrollEvent(components, mouseX, mouseY, amountX, amountY)) {
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amountX, amountY);
    }

    private boolean handleScrollEvent(List<IComponent<?>> components, double mouseX, double mouseY, double amountX, double amountY) {
        for (IComponent<?> component : new ArrayList<>(components)) {
            if (handleScrollEvent(component.getChildren(), mouseX, mouseY, amountX, amountY)) {
                return true;
            }
            if (component.preformOnScrollEvent(mouseX, mouseY, amountX, amountY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (handleKeyPressedEvent(components, keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private boolean handleKeyPressedEvent(List<IComponent<?>> components, int keyCode, int scanCode, int modifiers) {
        boolean handled = false;
        for (IComponent<?> component : new ArrayList<>(components)) {
            if (handleKeyPressedEvent(component.getChildren(), keyCode, scanCode, modifiers)) {
                handled = true;
                break;
            }
            if (component.preformOnKeyPressedEvent(keyCode, scanCode, modifiers)) {
                handled = true;
                break;
            }
        }
        return handled;
    }

    @Override
    public boolean charTyped(char c, int i) {
        if (handleCharEvent(components, c, i)) {
            return true;
        }
        return super.charTyped(c, i);
    }

    private boolean handleCharEvent(List<IComponent<?>> components, char c, int i) {
        boolean handled = false;
        for (IComponent<?> component : new ArrayList<>(components)) {
            if (handleCharEvent(component.getChildren(), c, i)) {
                handled = true;
                break;
            }
            if (component.preformOnCharTypedEvent(c, i)) {
                handled = true;
                break;
            }
        }
        return handled;
    }

    @Override
    public boolean mouseReleased(double d, double e, int i) {
        if (handleMouseReleaseEvent(components, d, e, i)) {
            return true;
        }
        return super.mouseReleased(d, e, i);
    }

    private boolean handleMouseReleaseEvent(List<IComponent<?>> components, double mouseX, double mouseY, int button) {
        for (IComponent<?> component : new ArrayList<>(components)) {
            if (handleMouseReleaseEvent(component.getChildren(), mouseX, mouseY, button)) {
                return true;
            }
            if (component.preformOnMouseReleaseEvent(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return isPauseScreen;
    }

    @Override
    public void setPauseScreen(boolean pauseScreen) {
        isPauseScreen = pauseScreen;
    }
}
