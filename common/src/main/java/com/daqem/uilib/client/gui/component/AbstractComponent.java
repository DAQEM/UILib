package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.action.OnClickAction;
import com.daqem.uilib.api.client.gui.component.action.OnHoverAction;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

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

    private @Nullable T hoverState;

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

        //noinspection unchecked
        this.hoverState = (T) this.getClone();
    }

    @Override
    public void start() {
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {

        if (isHovered(mouseX, mouseY) && hoverState != null && hoverState.isVisible() && getOnHoverAction() != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(hoverState.getX(), hoverState.getY(), hoverState.getZ());
            guiGraphics.pose().scale(hoverState.getScale(), hoverState.getScale(), hoverState.getScale());
            guiGraphics.pose().rotateAround(Axis.ZP.rotationDegrees(hoverState.getRotation()), hoverState.getWidth() / 2.0f, hoverState.getHeight() / 2.0f, 0.0f);
            guiGraphics.setColor(hoverState.getColorManipulator().getRed(), hoverState.getColorManipulator().getGreen(), hoverState.getColorManipulator().getBlue(), hoverState.getOpacity());
            hoverState.render(guiGraphics, mouseX, mouseY, partialTicks);
            guiGraphics.setColor(1F, 1F, 1F, 1F);
            guiGraphics.pose().popPose();
        } else if (isVisible()) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(getX(), getY(), getZ());
            guiGraphics.pose().scale(getScale(), getScale(), getScale());
            guiGraphics.pose().rotateAround(Axis.ZP.rotationDegrees(getRotation()), getWidth() / 2.0f, getHeight() / 2.0f, 0.0f);
            guiGraphics.setColor(getColorManipulator().getRed(), getColorManipulator().getGreen(), getColorManipulator().getBlue(), getOpacity());
            this.render(guiGraphics, mouseX, mouseY, partialTicks);
            guiGraphics.setColor(1F, 1F, 1F, 1F);
            guiGraphics.pose().popPose();
        }

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

    @Override
    public @Nullable OnClickAction<T> getOnClickAction() {
        return onClickAction;
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
