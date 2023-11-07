package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.action.OnClickAction;
import com.daqem.uilib.api.client.gui.component.action.OnHoverAction;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public abstract class AbstractComponent<T extends AbstractComponent<T>> implements IComponent<T> {

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

    private @Nullable OnClickAction<T> onClickAction;
    private @Nullable OnHoverAction<T> onHoverAction;

    @SuppressWarnings("unchecked")
    private @Nullable T hoverState = (T) this.getClone();

    private IColorManipulator colorManipulator = new ColorManipulator();

    public AbstractComponent(ITexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickAction<T> onClickAction, @Nullable OnHoverAction<T> onHoverAction) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.onClickAction = onClickAction;
        this.onHoverAction = onHoverAction;
    }

    @Override
    public void start() {
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), getZ());
        guiGraphics.pose().scale(getScale(), getScale(), getScale());
        guiGraphics.pose().rotateAround(new Quaternionf().rotateX(getRotation()), 0, 0, 1);
        guiGraphics.setColor(getColorManipulator().getRed(), getColorManipulator().getGreen(), getColorManipulator().getBlue(), getOpacity());
        if (isHovered(mouseX, mouseY) && hoverState != null) {
            hoverState.render(guiGraphics, mouseX, mouseY, partialTicks);
        } else {
            this.render(guiGraphics, mouseX, mouseY, partialTicks);
        }
        guiGraphics.setColor(1F, 1F, 1F, 1F);
        guiGraphics.pose().popPose();
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
    public int getY() {
        return y;
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
    public boolean isVisible() {
        return visible;
    }

    @Override
    public IColorManipulator getColorManipulator() {
        return colorManipulator;
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
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setOnClickAction(@Nullable OnClickAction<T> onClickAction) {
        this.onClickAction = onClickAction;
    }

    @Override
    public void setOnHoverAction(@Nullable OnHoverAction<T> onHoverAction) {
        this.onHoverAction = onHoverAction;
    }

    @Override
    public void setColorManipulator(IColorManipulator colorManipulator) {
        this.colorManipulator = colorManipulator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void preformOnClickAction() {
        if (onClickAction != null) {
            onClickAction.onClick((T) this);
        }
    }

    @Override
    public @Nullable OnClickAction<T> getOnClickAction() {
        return onClickAction;
    }

    @Override
    public void preformOnHoverAction() {
        if (onHoverAction != null) {
            onHoverAction.onHover(hoverState);
        }
    }

    @Override
    public @Nullable OnHoverAction<T> getOnHoverAction() {
        return onHoverAction;
    }

    @Override
    public @Nullable T getHoverState() {
        return hoverState;
    }

    @Override
    public void setHoverState(@Nullable T hoverState) {
        this.hoverState = hoverState;
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
}
