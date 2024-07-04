package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollContent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;


public class ScrollContentComponent extends AbstractComponent<ScrollContentComponent> implements IScrollContent {

    private int contentSpacing;
    private ScrollOrientation orientation;

    public ScrollContentComponent(int x, int y, int contentSpacing, ScrollOrientation orientation) {
        super(null, x, y, 0, 0);
        this.contentSpacing = contentSpacing;
        this.orientation = orientation;
    }

    // region Getters and Setters

    @Override
    public int getHeight() {
        if (getOrientation().isHorizontal()) {
            return componentMax(IComponent::getHeight);
        }
        return componentSum(IComponent::getHeight);
    }

    @Override
    public int getWidth() {
        if (getOrientation().isHorizontal()) {
            return componentSum(IComponent::getWidth);
        }
        return componentMax(IComponent::getWidth);
    }

    @Override
    public int getContentSpacing() {
        return contentSpacing;
    }

    @Override
    public void setContentSpacing(int contentSpacing) {
        this.contentSpacing = contentSpacing;
    }

    @Override
    public ScrollOrientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(ScrollOrientation orientation) {
        this.orientation = orientation;
    }

    // endregion

    // region Rendering

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        List<IComponent<?>> children = getChildren();
        IComponent<?> previousComponent = null;

        for (IComponent<?> component : children) {
            if (getOrientation().isHorizontal()) {
                int offsetX = (previousComponent != null) ? previousComponent.getX() + previousComponent.getWidth() + getContentSpacing() : 0;
                component.setX(offsetX);
            } else {
                int offsetY = (previousComponent != null) ? previousComponent.getY() + previousComponent.getHeight() + getContentSpacing() : 0;
                component.setY(offsetY);
            }
            component.setZ(10);
            previousComponent = component;
        }
        graphics.pose().pushPose();
        getChildren().forEach(component -> component.renderBase(graphics, mouseX, mouseY, delta));
        graphics.pose().popPose();
    }

    @Override
    public void renderTooltipsBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (getParent() instanceof ScrollPanelComponent scrollPanel) {
            if (scrollPanel.isTotalHovered(mouseX, mouseY)) {
                super.renderTooltipsBase(guiGraphics, mouseX, mouseY, delta);
            }
        } else {
            super.renderTooltipsBase(guiGraphics, mouseX, mouseY, delta);
        }
    }

    // endregion

    private int componentSum(Function<IComponent<?>, Integer> getSize) {
        return getChildren().stream().mapToInt(getSize::apply).sum() + (getChildren().size() - 1) * getContentSpacing();
    }

    private int componentMax(Function<IComponent<?>, Integer> getSize) {
        return getChildren().stream().mapToInt(getSize::apply).max().orElse(0);
    }

    public void scroll(ScrollPanelComponent scrolledObject, double amountX, double amountY) {
        amountY = -amountY; // Invert the scroll amount to match the scroll direction
        int min = 0;
        if (scrolledObject.getScrollOrientation().isHorizontal()) {
            int max = getWidth() - scrolledObject.getWidth();
            if (max < 0) {
                max = 0;
            }
            this.setX((int) Mth.clamp(this.getX() - (amountY * scrolledObject.getScrollSpeed()), -max, min));
        } else {
            int max = getHeight() - scrolledObject.getHeight();
            if (max < 0) {
                max = 0;
            }
            this.setY((int) Mth.clamp(this.getY() - (amountY * scrolledObject.getScrollSpeed()), -max, min));
        }
    }

    public void updateContentPositionBasedOnPercentage(ScrollPanelComponent scrollPanelComponent, double percentage) {
        int min = 0;
        if (scrollPanelComponent.getScrollOrientation().isHorizontal()) {
            int max = getWidth() - scrollPanelComponent.getWidth();
            double value = (getWidth() - scrollPanelComponent.getWidth()) * (percentage / 100D);
            this.setX((int) Mth.clamp(-value, -max, min));
        } else {
            int max = getHeight() - scrollPanelComponent.getHeight();
            double value = (getHeight() - scrollPanelComponent.getHeight()) * (percentage / 100D);
            this.setY((int) Mth.clamp(-value, -max, min));
        }
    }

    // region Event Pass Through

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (handleClickEvent(getChildren(), mouseX, mouseY, button)) {
            return true;
        }
        return super.preformOnClickEvent(mouseX, mouseY, button);
    }

    private boolean handleClickEvent(List<IComponent<?>> components, double mouseX, double mouseY, int button) {
        for (IComponent<?> component : components) {
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
    public void preformOnHoverEvent(double mouseX, double mouseY, float delta) {
        handleHoverEvent(getChildren(), mouseX, mouseY, delta);
        super.preformOnHoverEvent(mouseX, mouseY, delta);
    }

    private void handleHoverEvent(List<IComponent<?>> components, double mouseX, double mouseY, float delta) {
        for (IComponent<?> component : components) {
            handleHoverEvent(component.getChildren(), mouseX, mouseY, delta);
            component.preformOnHoverEvent(mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean preformOnScrollEvent(double mouseX, double mouseY, double amountX, double amountY) {
        if (handleScrollEvent(getChildren(), mouseX, mouseY, amountX, amountY)) {
            return true;
        }
        return super.preformOnScrollEvent(mouseX, mouseY, amountX, amountY);
    }

    private boolean handleScrollEvent(List<IComponent<?>> components, double mouseX, double mouseY, double amountX, double amountY) {
        for (IComponent<?> component : components) {
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
    public boolean preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (handleDragEvent(getChildren(), mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }
        return super.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
    }

    private boolean handleDragEvent(List<IComponent<?>> components, double mouseX, double mouseY, int button, double dragX, double dragY) {
        for (IComponent<?> component : components) {
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
    public boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        if (handleKeyPressEvent(getChildren(), keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.preformOnKeyPressedEvent(keyCode, scanCode, modifiers);
    }

    private boolean handleKeyPressEvent(List<IComponent<?>> components, int keyCode, int scanCode, int modifiers) {
        for (IComponent<?> component : components) {
            if (handleKeyPressEvent(component.getChildren(), keyCode, scanCode, modifiers)) {
                return true;
            }
            if (component.preformOnKeyPressedEvent(keyCode, scanCode, modifiers)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preformOnCharTypedEvent(char typedChar, int modifiers) {
        if (handleCharTypedEvent(getChildren(), typedChar, modifiers)) {
            return true;
        }
        return super.preformOnCharTypedEvent(typedChar, modifiers);
    }

    private boolean handleCharTypedEvent(List<IComponent<?>> components, char typedChar, int modifiers) {
        for (IComponent<?> component : components) {
            if (handleCharTypedEvent(component.getChildren(), typedChar, modifiers)) {
                return true;
            }
            if (component.preformOnCharTypedEvent(typedChar, modifiers)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preformOnMouseReleaseEvent(double mouseX, double mouseY, int button) {
        if (handleMouseReleaseEvent(getChildren(), mouseX, mouseY, button)) {
            return true;
        }
        return super.preformOnMouseReleaseEvent(mouseX, mouseY, button);
    }

    private boolean handleMouseReleaseEvent(List<IComponent<?>> components, double mouseX, double mouseY, int button) {
        for (IComponent<?> component : components) {
            if (handleMouseReleaseEvent(component.getChildren(), mouseX, mouseY, button)) {
                return true;
            }
            if (component.preformOnMouseReleaseEvent(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    // endregion
}
