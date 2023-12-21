package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnDragEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.event.OnScrollEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent<T extends AbstractComponent<T>> implements IComponent<T> {

    private @Nullable IComponent<?> parent;
    private List<IComponent<?>> children = new ArrayList<>();
    private ITexture texture;
    private int x;
    private int y;
    private int z = 0;
    private int width;
    private int height;
    private @Nullable IText<?> text;
    private float scale = 1;
    private float opacity = 1;
    private float rotation = 0;
    private boolean visible = true;
    private boolean centeredHorizontally = false;
    private boolean centeredVertically = false;
    private boolean renderBeforeParent = false;

    private @Nullable OnClickEvent<T> onClickEvent;
    private @Nullable OnHoverEvent<T> onHoverEvent;
    private @Nullable OnDragEvent<T> onDragEvent;
    private @Nullable OnScrollEvent<T> onScrollEvent;

    private @Nullable T hoverState;

    private IColorManipulator colorManipulator = new ColorManipulator();

    public AbstractComponent(ITexture texture, int x, int y, int width, int height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //noinspection unchecked
        this.hoverState = (T) this.getClone();
    }

    @Override
    public void startRenderable() {
        if (getText() != null) {
            getText().startRenderable();
        }

        getChildren().forEach(IRenderable::startRenderable);
    }

    @Override
    public void resizeScreenRepositionRenderable(int width, int height) {
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        if (isHovered(mouseX, mouseY) && hoverState != null && hoverState.isVisible() && getOnHoverEvent() != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(hoverState.getX(), hoverState.getY(), hoverState.getZ());
            guiGraphics.pose().scale(hoverState.getScale(), hoverState.getScale(), hoverState.getScale());
            guiGraphics.pose().rotateAround(Axis.ZP.rotationDegrees(hoverState.getRotation()), hoverState.getWidth() / 2.0f, hoverState.getHeight() / 2.0f, 0.0f);
            guiGraphics.setColor(hoverState.getColorManipulator().getRed(), hoverState.getColorManipulator().getGreen(), hoverState.getColorManipulator().getBlue(), hoverState.getOpacity());
            hoverState.getChildren().stream().filter(IComponent::renderBeforeParent).forEach(child -> child.renderBase(guiGraphics, mouseX, mouseY, delta));
            hoverState.render(guiGraphics, mouseX, mouseY, delta);
            hoverState.getChildren().stream().filter(x -> !x.renderBeforeParent()).forEach(child -> child.renderBase(guiGraphics, mouseX, mouseY, delta));
            guiGraphics.setColor(1F, 1F, 1F, 1F);
            guiGraphics.pose().popPose();
        } else if (isVisible()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(getX(), getY(), getZ());
            guiGraphics.pose().scale(getScale(), getScale(), getScale());
            guiGraphics.pose().rotateAround(Axis.ZP.rotationDegrees(getRotation()), getWidth() / 2.0f, getHeight() / 2.0f, 0.0f);
            guiGraphics.setColor(getColorManipulator().getRed(), getColorManipulator().getGreen(), getColorManipulator().getBlue(), getOpacity());
            this.getChildren().stream().filter(IComponent::renderBeforeParent).forEach(child -> child.renderBase(guiGraphics, mouseX, mouseY, delta));
            this.render(guiGraphics, mouseX, mouseY, delta);
            this.getChildren().stream().filter(x -> !x.renderBeforeParent()).forEach(child -> child.renderBase(guiGraphics, mouseX, mouseY, delta));
            if (getText() != null) {
                getText().renderBase(guiGraphics, mouseX, mouseY, delta);
            }
            guiGraphics.setColor(1F, 1F, 1F, 1F);
            guiGraphics.pose().popPose();
        }
    }


    @Override
    public @Nullable IComponent<?> getParent() {
        return parent;
    }

    @Override
    public List<IComponent<?>> getChildren() {
        return children;
    }

    @Override
    public ITexture getTexture() {
        return texture;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getTotalX() {
        int totalX = getX();
        @Nullable IComponent<?> parent = getParent();
        while (parent != null) {
            totalX += parent.getX();
            parent = parent.getParent();
        }
        return totalX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getTotalY() {
        int totalY = getY();
        @Nullable IComponent<?> parent = getParent();
        while (parent != null) {
            totalY += parent.getY();
            parent = parent.getParent();
        }
        return totalY;
    }

    @Override
    public int getZ() {
        return z;
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
    public @Nullable IText<?> getText() {
        return text;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public float getOpacity() {
        return opacity;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public boolean renderBeforeParent() {
        return renderBeforeParent;
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
    public void setParent(@Nullable IComponent<?> parent, boolean addAsChild) {
        this.parent = parent;
        if (addAsChild && parent != null && !parent.getChildren().contains(this)) {
            parent.addChild(this);
        }
    }

    @Override
    public void setChildren(List<IComponent<?>> children) {
        this.children = children;
        this.children.forEach(child -> child.setParent(this, true));
    }

    @Override
    public void addChild(IComponent<?> child) {
        children.add(child);
        child.setParent(this, true);
    }

    @Override
    public void addChildren(IComponent<?>... children) {
        this.children.addAll(List.of(children));
        this.children.forEach(child -> child.setParent(this, true));
    }

    @Override
    public void addChildren(List<IComponent<?>> children) {
        this.children.addAll(children);
        this.children.forEach(child -> child.setParent(this, true));
    }

    @Override
    public void removeChild(IComponent<?> child) {
        children.remove(child);
        child.setParent(null, false);
    }

    @Override
    public void setTexture(ITexture texture) {
        this.texture = texture;
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
    public void setZ(int z) {
        this.z = z;
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
    public void setText(@Nullable IText<?> text) {
        this.text = text;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setRenderBeforeParent(boolean renderBeforeParent) {
        this.renderBeforeParent = renderBeforeParent;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setOnClickEvent(@Nullable OnClickEvent<T> onClickEvent) {
        this.onClickEvent = onClickEvent;
    }

    @Override
    public void setOnHoverEvent(@Nullable OnHoverEvent<T> onHoverEvent) {
        this.onHoverEvent = onHoverEvent;
    }

    @Override
    public void setColorManipulator(IColorManipulator colorManipulator) {
        this.colorManipulator = colorManipulator;
    }

    @Override
    public @Nullable OnClickEvent<T> getOnClickEvent() {
        return onClickEvent;
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= getX() && mouseX <= getX() + (getWidth() * getScale()) && mouseY >= getY() && mouseY <= getY() + (getHeight() * getScale());
    }

    @Override
    public @Nullable OnHoverEvent<T> getOnHoverEvent() {
        return onHoverEvent;
    }

    @Override
    public @Nullable T getHoverState() {
        return hoverState;
    }

    @Override
    public void setHoverState(@Nullable T hoverState) {
        this.hoverState = hoverState;
    }

    @Override
    public boolean isCenteredHorizontally() {
        return centeredHorizontally;
    }

    @Override
    public boolean isCenteredVertically() {
        return centeredVertically;
    }

    @Override
    public boolean isCentered() {
        return isCenteredHorizontally() && isCenteredVertically();
    }

    @Override
    public void centerHorizontally() {
        int containerWidth = getParentWidth();
        int componentWidth = getWidth();
        setX((int) ((containerWidth / 2) - ((componentWidth * scale) / 2)));
        this.centeredHorizontally = true;
    }

    @Override
    public void centerVertically() {
        int containerHeight = getParentHeight();
        int componentHeight = getHeight();
        setY((int) ((containerHeight / 2) - ((componentHeight * scale) / 2)));
        this.centeredVertically = true;
    }

    @Override
    public void center() {
        centerHorizontally();
        centerVertically();
    }

    private int getParentWidth() {
        Screen screen = Minecraft.getInstance().screen;
        return getParent() != null ? getParent().getWidth() : screen != null ? screen.width : 0;
    }

    private int getParentHeight() {
        Screen screen = Minecraft.getInstance().screen;
        return getParent() != null ? getParent().getHeight() : screen != null ? screen.height : 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable Object getClone() {
        try {
            T clone = (T) this.clone();

            IText<?> currentText = getText();
            if (currentText != null)
                clone.setText((IText<?>) currentText.getClone());

            ITexture currentTexture = getTexture();
            if (currentTexture != null)
                clone.setTexture((ITexture) currentTexture.getClone());

            clone.setColorManipulator((IColorManipulator) getColorManipulator().getClone());

            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public OnDragEvent<T> getOnDragEvent() {
        return onDragEvent;
    }

    @Override
    public void setOnDragEvent(OnDragEvent<T> onDragEvent) {
        this.onDragEvent = onDragEvent;
    }

    @Override
    public OnScrollEvent<T> getOnScrollEvent() {
        return onScrollEvent;
    }

    @Override
    public void setOnScrollEvent(OnScrollEvent<T> onScrollEvent) {
        this.onScrollEvent = onScrollEvent;
    }
}
