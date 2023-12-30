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

    // endregion

    private int componentSum(Function<IComponent<?>, Integer> getSize) {
        return getChildren().stream().mapToInt(getSize::apply).sum() + (getChildren().size() - 1) * getContentSpacing();
    }

    private int componentMax(Function<IComponent<?>, Integer> getSize) {
        return getChildren().stream().mapToInt(getSize::apply).max().orElse(0);
    }

    public void scroll(ScrollPanelComponent scrolledObject, double delta) {
        int min = 0;
        if (scrolledObject.getScrollOrientation().isHorizontal()) {
            int max = getWidth() - scrolledObject.getWidth();
            if (max < 0) {
                max = 0;
            }
            this.setX((int) Mth.clamp(this.getX() - (delta * scrolledObject.getScrollSpeed()), -max, min));
        } else {
            int max = getHeight() - scrolledObject.getHeight();
            if (max < 0) {
                max = 0;
            }
            this.setY((int) Mth.clamp(this.getY() - (delta * scrolledObject.getScrollSpeed()), -max, min));
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
}
