package com.daqem.uilib.client.gui.text;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnDragEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.event.OnScrollEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractText<T extends AbstractText<T>> implements IText<T> {

    private Font font;

    private Component text;
    private int x;
    private int y;
    private int width;
    private int height;
    private int textColor = 0xFFFFFFFF;
    private boolean shadow;
    private boolean visible = true;

    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean strikethrough;
    private boolean obfuscated;

    private boolean horizontalCenter;
    private boolean verticalCenter;

    private @Nullable OnClickEvent<T> onClickEvent;
    private @Nullable OnHoverEvent<T> onHoverEvent;
    private @Nullable OnDragEvent<T> onDragEvent;
    private @Nullable OnScrollEvent<T> onScrollEvent;

    private @Nullable T hoverState;

    public AbstractText(Font font, Component text, int x, int y) {
        this(font, text, x, y, font.width(text), font.lineHeight);
    }

    public AbstractText(Font font, Component text, int x, int y, int width, int height) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //noinspection unchecked
        this.hoverState = (T) this.getClone();
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public Component getText() {
        return text;
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
    public int getTextColor() {
        return textColor;
    }

    @Override
    public boolean isShadow() {
        return shadow;
    }

    @Override
    public boolean isBold() {
        return bold;
    }

    @Override
    public boolean isItalic() {
        return italic;
    }

    @Override
    public boolean isUnderlined() {
        return underlined;
    }

    @Override
    public boolean isStrikethrough() {
        return strikethrough;
    }

    @Override
    public boolean isObfuscated() {
        return obfuscated;
    }

    @Override
    public boolean isHorizontalCenter() {
        return horizontalCenter;
    }

    @Override
    public boolean isVerticalCenter() {
        return verticalCenter;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void setText(Component text) {
        this.text = text;
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
    public void setTextColor(int color) {
        this.textColor = color;
    }

    @Override
    public void setTextColor(ChatFormatting chatFormatting) {
        Integer color = chatFormatting.getColor();
        if (color != null) {
            this.textColor = color;
        }
    }

    @Override
    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    @Override
    public void setBold(boolean bold) {
        this.bold = bold;
    }

    @Override
    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    @Override
    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    @Override
    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    @Override
    public void setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
    }

    @Override
    public void setHorizontalCenter(boolean horizontalCenter) {
        this.horizontalCenter = horizontalCenter;
    }

    @Override
    public void setVerticalCenter(boolean verticalCenter) {
        this.verticalCenter = verticalCenter;
    }

    @Override
    public void setCenter(boolean horizontalCenter, boolean verticalCenter) {
        this.horizontalCenter = horizontalCenter;
        this.verticalCenter = verticalCenter;
    }

    @Override
    public void startRenderable() {
    }

    @Override
    public void resizeScreenRepositionRenderable(int width, int height) {
    }

    @Override
    public void renderBase(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (isVisible()) {
            graphics.pose().pushPose();

            graphics.pose().translate(
                    x + (isHorizontalCenter() ? ((float) getWidth() / 2) - ((float) getFont().width(getText()) / 2) : 0),
                    y + (isVerticalCenter() ? ((float) getHeight() / 2) - ((float) getFont().lineHeight / 2) : 0),
                    0);
            Style style = this.getText().getStyle().withColor(getTextColor()).withBold(isBold()).withItalic(isItalic())
                    .withUnderlined(isUnderlined()).withStrikethrough(isStrikethrough()).withObfuscated(isObfuscated());
            this.setText(this.getText().copy().setStyle(style));
            this.render(graphics, mouseX, mouseY, delta);
            graphics.pose().popPose();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable Object getClone() {
        try {
            T clone = (T) super.clone();
            if (this.getText() != null)
                clone.setText(this.getText().copy());
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
    public @Nullable OnHoverEvent<T> getOnHoverEvent() {
        return onHoverEvent;
    }

    @Override
    public void setOnHoverEvent(@Nullable OnHoverEvent<T> onHoverEvent) {
        this.onHoverEvent = onHoverEvent;
    }

    @Override
    public void setHoverState(@Nullable T component) {
        this.hoverState = component;
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
}
