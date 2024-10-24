package com.daqem.uilib.client.gui.background;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import com.daqem.uilib.api.client.gui.component.event.*;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBackground<T extends AbstractBackground<T>> implements IBackground<T> {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible = true;
    private IColorManipulator colorManipulator = new ColorManipulator();

    private @Nullable OnClickEvent<T> onClickEvent;
    private @Nullable OnHoverEvent<T> onHoverEvent;
    private @Nullable OnDragEvent<T> onDragEvent;
    private @Nullable OnScrollEvent<T> onScrollEvent;
    private @Nullable OnKeyPressedEvent<T> onKeyPressedEvent;
    private @Nullable OnCharTypedEvent<T> onCharTypedEvent;
    private @Nullable OnMouseReleaseEvent<T> onMouseReleaseEvent;

    private @Nullable T hoverState;

    public AbstractBackground(int width, int height) {
        this(0, 0, width, height);
    }

    public AbstractBackground(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //noinspection unchecked
        this.hoverState = (T) this.getClone();
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
    public int getTotalX() {
        return getX();
    }

    @Override
    public int getTotalY() {
        return getY();
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
    public boolean isVisible() {
        return visible;
    }

    @Override
    public IColorManipulator getColorManipulator() {
        return colorManipulator;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setColorManipulator(IColorManipulator colorManipulator) {
        this.colorManipulator = colorManipulator;
    }

    @Override
    public void startRenderable() {
    }

    @Override
    public void resizeScreenRepositionRenderable(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), -1);
        guiGraphics.setColor(colorManipulator.getRed(), colorManipulator.getGreen(), colorManipulator.getBlue(), colorManipulator.getOpacity());
        this.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.setColor(1F, 1F, 1F, 1F);
        guiGraphics.pose().popPose();
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable Object getClone() {
        try {
            T clone = (T) this.clone();
            clone.setColorManipulator((IColorManipulator) getColorManipulator().getClone());
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public @Nullable OnClickEvent<T> getOnClickEvent() {
        return onClickEvent;
    }

    @Override
    public void setOnClickEvent(@Nullable OnClickEvent<T> onClickEvent) {
        this.onClickEvent = onClickEvent;
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight();
    }

    @Override
    public boolean isTotalHovered(double mouseX, double mouseY) {
        return isHovered(mouseX, mouseY);
    }

    @Override
    public @Nullable OnHoverEvent<T> getOnHoverEvent() {
        return onHoverEvent;
    }

    @Override
    public void setOnHoverEvent(@Nullable OnHoverEvent<T> onHoverEvent) {
        this.onHoverEvent = onHoverEvent;
    }

    @Override
    public void setHoverState(@Nullable T hoverState) {
        this.hoverState = hoverState;
    }

    @Override
    public @Nullable T getHoverState() {
        return hoverState;
    }

    @Override
    public @Nullable OnDragEvent<T> getOnDragEvent() {
        return onDragEvent;
    }

    @Override
    public void setOnDragEvent(@Nullable OnDragEvent<T> onDragEvent) {
        this.onDragEvent = onDragEvent;
    }

    @Override
    public @Nullable OnScrollEvent<T> getOnScrollEvent() {
        return onScrollEvent;
    }

    @Override
    public void setOnScrollEvent(@Nullable OnScrollEvent<T> onScrollEvent) {
        this.onScrollEvent = onScrollEvent;
    }

    @Override
    public @Nullable OnKeyPressedEvent<T> getOnKeyPressedEvent() {
        return onKeyPressedEvent;
    }

    @Override
    public void setOnKeyPressedEvent(@Nullable OnKeyPressedEvent<T> onKeyPressedEvent) {
        this.onKeyPressedEvent = onKeyPressedEvent;
    }

    @Override
    public @Nullable OnCharTypedEvent<T> getOnCharTypedEvent() {
        return onCharTypedEvent;
    }

    @Override
    public void setOnCharTypedEvent(@Nullable OnCharTypedEvent<T> onCharTypedEvent) {
        this.onCharTypedEvent = onCharTypedEvent;
    }

    @Override
    public @Nullable OnMouseReleaseEvent<T> getOnMouseReleaseEvent() {
        return onMouseReleaseEvent;
    }

    @Override
    public void setOnMouseReleaseEvent(@Nullable OnMouseReleaseEvent<T> onMouseReleaseEvent) {
        this.onMouseReleaseEvent = onMouseReleaseEvent;
    }
}
