package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.scroll.IScrollBar;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;

import java.util.function.BiPredicate;

public class ScrollWheelComponent extends AbstractNineSlicedComponent<ScrollWheelComponent> implements IScrollBar {

    private int scrollValue = 0;

    public ScrollWheelComponent(NineSlicedTexture texture, int x, int y, int thickness) {
        super(texture, x, y, thickness, thickness, null, null, null);
    }

    @Override
    public int getMinValue(ScrollPanelComponent scrollPanelComponent) {
        return 0;
    }

    @Override
    public int getMaxValue(ScrollPanelComponent scrollPanelComponent) {
        return scrollPanelComponent.getOffScreenContentLength();
    }

    @Override
    public int getScrollValue() {
        return scrollValue;
    }

    @Override
    public void setScrollValue(int scrollValue) {
        this.scrollValue = scrollValue;
    }

    @Override
    public int getNextStep(ScrollPanelComponent scrollPanelComponent) {
        if (scrollPanelComponent.getScrollType().isSmooth())  return 1;

        BiPredicate<IComponent<?>, Integer> positionCheck = (component, scrollValue) -> scrollPanelComponent.getScrollOrientation().isHorizontal() ? component.getX() > scrollValue : component.getY() > scrollValue;

        return calculateStepValue(scrollPanelComponent, positionCheck, getMaxValue(scrollPanelComponent));
    }

    @Override
    public int getPreviousStep(ScrollPanelComponent scrollPanelComponent) {
        if (scrollPanelComponent.getScrollType().isSmooth()) return 1;

        BiPredicate<IComponent<?>, Integer> positionCheck = (component, scrollValue) -> scrollPanelComponent.getScrollOrientation().isHorizontal() ? component.getX() < scrollValue : component.getY() < scrollValue;

        return calculateStepValue(scrollPanelComponent, positionCheck, getMinValue(scrollPanelComponent));
    }

    @Override
    public int getDimension(boolean isHorizontal) {
        return isHorizontal ? getWidth() : getHeight();
    }

    private int calculateStepValue(ScrollPanelComponent scrollPanelComponent, BiPredicate<IComponent<?>, Integer> positionCheck, int fallbackValue) {
        return scrollPanelComponent.getScrollContentComponent().map(scrollContentComponent -> scrollContentComponent.getChildren().stream()
                .filter(component -> positionCheck.test(component, getScrollValue()))
                .findFirst()
                .map(component -> scrollPanelComponent.getScrollOrientation().isHorizontal() ? component.getX() : component.getY())
                .orElse(fallbackValue)).orElse(0);
    }

    private int getStep(ScrollPanelComponent scrollPanelComponent, double delta) {
        if (delta == 0) return 0;

        int stepDirection = delta < 0 ? getPreviousStep(scrollPanelComponent) : getNextStep(scrollPanelComponent);
        int flooredDelta = Mth.floor(delta);
        int speedAdjustedDelta = (int) (flooredDelta * scrollPanelComponent.getScrollSpeed());

        return speedAdjustedDelta * stepDirection;
    }

    @Override
    public void scroll(ScrollPanelComponent scrollPanelComponent, double delta) {
        double scrollStep = getStep(scrollPanelComponent, delta);
        int newScrollPosition = (int) (getScrollValue() + scrollStep);

        int minValue = getMinValue(scrollPanelComponent);
        int maxValue = getMaxValue(scrollPanelComponent);

        if (newScrollPosition < minValue) {
            newScrollPosition = minValue;
        } else if (newScrollPosition > maxValue) {
            newScrollPosition = maxValue;
        }

        setScrollValue(newScrollPosition);
    }
}
