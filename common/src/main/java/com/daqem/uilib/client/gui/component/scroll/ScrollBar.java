package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollBar;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollContent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollType;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.background.NineSlicedBackground;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

public class ScrollBar extends AbstractNineSlicedComponent<ScrollBar> implements IScrollBar {

    private int value = 0;

    public ScrollBar(NineSlicedTexture texture, int x, int y, int thickness) {
        this(texture, x, y, thickness, null, null);
    }

    public ScrollBar(NineSlicedTexture texture, int x, int y, int thickness, @Nullable OnClickEvent<ScrollBar> onClickEvent, @Nullable OnHoverEvent<ScrollBar> onHoverEvent) {
        super(texture, x, y, thickness, thickness, null, onClickEvent, onHoverEvent);
    }

    @Override
    public int getMinValue(ScrollPaneComponent scrolledObject) {
        return 0;
    }

    @Override
    public int getMaxValue(ScrollPaneComponent scrolledObject) {
        return scrolledObject.getContent() == null ? 0 : scrolledObject.getOrientation() == ScrollOrientation.HORIZONTAL ? scrolledObject.getContent().getWidth() - scrolledObject.getWidth() : scrolledObject.getContent().getHeight() - scrolledObject.getHeight();
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
    public int getNextStep(ScrollPaneComponent scrolledObject) {
        return scrolledObject.getScrollType() == ScrollType.SMOOTH ? 1 : scrolledObject.getContent() == null ? 0 : scrolledObject.getContent().getComponents().stream()
                .filter(component -> scrolledObject.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() > getValue() : component.getY() > getValue())
                .findFirst()
                .map(component -> scrolledObject.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() : component.getY())
                .orElse(getMaxValue(scrolledObject));
    }

    @Override
    public int getPreviousStep(ScrollPaneComponent scrolledObject) {
        return scrolledObject.getScrollType() == ScrollType.SMOOTH ? 1 : scrolledObject.getContent() == null ? 0 : scrolledObject.getContent().getComponents().stream()
                .filter(component -> scrolledObject.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() < getValue() : component.getY() < getValue())
                .findFirst()
                .map(component -> scrolledObject.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() : component.getY())
                .orElse(getMinValue(scrolledObject));
    }

    @Override
    public void scroll(ScrollPaneComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta) {
        if (getValue() + delta < getMinValue(scrolledObject)) {
            setValue(getMinValue(scrolledObject));
        } else if (getValue() + delta > getMaxValue(scrolledObject)) {
            setValue(getMaxValue(scrolledObject));
        } else {
            setValue((int) (getValue() + delta));
        }
    }
}
