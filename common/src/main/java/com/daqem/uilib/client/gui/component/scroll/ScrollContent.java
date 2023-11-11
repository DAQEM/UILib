package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollContent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ScrollContent extends AbstractComponent<ScrollContent> implements IScrollContent {

    private final List<IComponent<?>> components = new ArrayList<>();
    private int contentSpacing;
    private ScrollOrientation orientation;

    public ScrollContent(int x, int y, int width, int height, int contentSpacing, ScrollOrientation orientation) {
        this(x, y, width, height, null, null, null, contentSpacing, orientation);
    }

    public ScrollContent(int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<ScrollContent> onClickEvent, @Nullable OnHoverEvent<ScrollContent> onHoverEvent, int contentSpacing, ScrollOrientation orientation) {
        super(null, x, y, width, height, text, onClickEvent, onHoverEvent);
        this.contentSpacing = contentSpacing;
        this.orientation = orientation;
    }

    @Override
    public List<IComponent<?>> getComponents() {
        return components;
    }

    @Override
    public void addComponent(@NotNull IComponent<?> component) {
        if (!components.contains(component)) {
            components.add(component);
        }
    }

    @Override
    public void removeComponent(IComponent<?> component) {
        components.remove(component);
    }

    @Override
    public void clearComponents() {
        components.clear();
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

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        for (int i = 0; i < components.size(); i++) {
            IComponent<?> component = components.get(i);
            @Nullable IComponent<?> previousComponent = i > 0 ? components.get(i - 1) : null;
            if (orientation == ScrollOrientation.VERTICAL) {
                int offsetY = previousComponent != null ? previousComponent.getY() + previousComponent.getHeight() + contentSpacing : 0;
                component.setY(offsetY);
            } else {
                int offsetX = previousComponent != null ? previousComponent.getX() + previousComponent.getWidth() + contentSpacing : 0;
                component.setX(offsetX);
            }
        }
        graphics.pose().pushPose();
        components.forEach(component -> component.render(graphics, mouseX, mouseY, delta));
        graphics.pose().popPose();
    }
}
