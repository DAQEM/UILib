package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollBar;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollContent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollType;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.background.NineSlicedBackground;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

public class ScrollBar extends AbstractNineSlicedComponent<ScrollBar> implements IScrollBar {

    private IScrollContent content;
    private ScrollOrientation orientation;
    private ScrollType scrollType = ScrollType.SMOOTH;
    private int minValue = 0;
    private int maxValue = 0;

    private int value = 0;

    public ScrollBar(NineSlicedTexture texture, int x, int y, int width, int height, ScrollOrientation orientation, IScrollContent content) {
        this(texture, x, y, width, height, null, null, orientation, content);
    }

    public ScrollBar(NineSlicedTexture texture, int x, int y, int width, int height, @Nullable OnClickEvent<ScrollBar> onClickEvent, @Nullable OnHoverEvent<ScrollBar> onHoverEvent, ScrollOrientation orientation, IScrollContent content) {
        super(texture, x, y, width, height, null, onClickEvent, onHoverEvent);
        this.orientation = orientation;
        this.content = content;
    }

    @Override
    public IScrollContent getContent() {
        return content;
    }

    @Override
    public void setContent(IScrollContent content) {
        this.content = content;
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
    public ScrollType getScrollType() {
        return scrollType;
    }

    @Override
    public void setScrollType(ScrollType scrollType) {
        this.scrollType = scrollType;
    }

    @Override
    public int getMinValue() {
        return minValue;
    }

    @Override
    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    @Override
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getNextStep() {
        return getScrollType() == ScrollType.SMOOTH ? 1 : getContent().getComponents().stream()
                .filter(component -> getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() > getValue() : component.getY() > getValue())
                .findFirst()
                .map(component -> getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() : component.getY())
                .orElse(getMaxValue());
    }

    @Override
    public int getPreviousStep() {
        return getScrollType() == ScrollType.SMOOTH ? 1 : getContent().getComponents().stream()
                .filter(component -> getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() < getValue() : component.getY() < getValue())
                .findFirst()
                .map(component -> getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() : component.getY())
                .orElse(getMinValue());
    }

    @Override
    public int getScrollDistance() {
        return getOrientation() == ScrollOrientation.HORIZONTAL ? getWidth() : getHeight();
    }

    @Override
    public void scroll(ScrollPaneComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta) {
        if (getOrientation() == ScrollOrientation.HORIZONTAL) {
            if (mouseX > getX() && mouseX < getX() + getWidth()) {
                if (mouseY > getY() && mouseY < getY() + getHeight()) {
                    if (mouseX > getX() + getWidth() / 2F) {
                        if (getValue() < getMaxValue()) {
                            setValue(getNextStep());
                        }
                    } else {
                        if (getValue() > getMinValue()) {
                            setValue(getPreviousStep());
                        }
                    }
                }
            }
        } else {
            if (mouseY > getY() && mouseY < getY() + getHeight()) {
                if (mouseX > getX() && mouseX < getX() + getWidth()) {
                    if (mouseY > getY() + getHeight() / 2F) {
                        if (getValue() < getMaxValue()) {
                            setValue(getNextStep());
                        }
                    } else {
                        if (getValue() > getMinValue()) {
                            setValue(getPreviousStep());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.pose().pushPose();
        if (getOrientation() == ScrollOrientation.HORIZONTAL) {
            graphics.pose().translate(getX() + getValue(), getY(), getZ());
        } else {
            graphics.pose().translate(getX(), getY() + getValue(), getZ());
        }
        super.render(graphics, mouseX, mouseY, delta);
        graphics.pose().popPose();
    }
}
