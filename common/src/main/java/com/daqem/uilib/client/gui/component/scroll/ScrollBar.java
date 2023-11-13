package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollBar;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollType;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
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
    public int getMinValue(ScrollPaneComponent scrollPaneComponent) {
        return 0;
    }

    @Override
    public int getMaxValue(ScrollPaneComponent scrollPaneComponent) {
        return scrollPaneComponent.getContent() == null ? 0 : scrollPaneComponent.getOrientation() == ScrollOrientation.HORIZONTAL ? scrollPaneComponent.getContent().getWidth() - scrollPaneComponent.getWidth() : scrollPaneComponent.getContent().getHeight() - scrollPaneComponent.getHeight();
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
    public int getNextStep(ScrollPaneComponent scrollPaneComponent) {
        return scrollPaneComponent.getScrollType() == ScrollType.SMOOTH ? 1 : scrollPaneComponent.getContent() == null ? 0 : scrollPaneComponent.getContent().getChildren().stream()
                .filter(component -> scrollPaneComponent.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() > getValue() : component.getY() > getValue())
                .findFirst()
                .map(component -> scrollPaneComponent.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() : component.getY())
                .orElse(getMaxValue(scrollPaneComponent));
    }

    @Override
    public int getPreviousStep(ScrollPaneComponent scrollPaneComponent) {
        return scrollPaneComponent.getScrollType() == ScrollType.SMOOTH ? 1 : scrollPaneComponent.getContent() == null ? 0 : scrollPaneComponent.getContent().getChildren().stream()
                .filter(component -> scrollPaneComponent.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() < getValue() : component.getY() < getValue())
                .findFirst()
                .map(component -> scrollPaneComponent.getOrientation() == ScrollOrientation.HORIZONTAL ? component.getX() : component.getY())
                .orElse(getMinValue(scrollPaneComponent));
    }

    @Override
    public void scroll(ScrollPaneComponent scrollPaneComponent, Screen screen, double mouseX, double mouseY, double delta) {
        double scrollAmount = (delta * scrollPaneComponent.getScrollSpeed());
        if (getValue() + scrollAmount < getMinValue(scrollPaneComponent)) {
            setValue(getMinValue(scrollPaneComponent));
        } else if (getValue() + scrollAmount > getMaxValue(scrollPaneComponent)) {
            setValue(getMaxValue(scrollPaneComponent));
        } else {
            setValue((int) (getValue() + scrollAmount));
        }
    }
}
