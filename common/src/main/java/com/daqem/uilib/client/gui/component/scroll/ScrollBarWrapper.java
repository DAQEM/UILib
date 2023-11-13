package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class ScrollBarWrapper extends AbstractNineSlicedComponent<ScrollBarWrapper> {

    private @Nullable ScrollBar scrollBar;
    private @Nullable IBackground<?> background;
    private ScrollOrientation orientation;
    private boolean centerBackground = true;

    public ScrollBarWrapper(int x, int y, int width, int height, ScrollOrientation orientation) {
        this(x, y, width, height, orientation, new ScrollBar(Textures.SCROLL_BAR, 0, 0, orientation == ScrollOrientation.HORIZONTAL ? width : height));
    }

    public ScrollBarWrapper(int x, int y, int width, int height, ScrollOrientation orientation, @Nullable ScrollBar scrollBar) {
        super(null, x, y, width, height, null, null, null);
        this.scrollBar = scrollBar;
        this.orientation = orientation;
        this.background = Backgrounds.getSolidScrollBarBackground(this);
        this.setZ(1);
    }

    @Override
    public void startRenderable() {
        super.startRenderable();
        if (getParent() instanceof ScrollPaneComponent parent) {
            setOnScrollEvent((scrolledObject, screen, mouseX, mouseY, delta) -> {
                    scroll(parent, screen, mouseX, mouseY, delta);
                    if (parent.getContent() != null) {
                        parent.getContent().scroll(parent, screen, mouseX, mouseY, delta);
                    }
            });
            setOnDragEvent((draggedObject, screen, mouseX, mouseY, button, dragX, dragY) -> {
                ScrollBar innerScrollBar = getScrollBar();
                if (innerScrollBar != null) {
                    double percentage = getDragPercentage(mouseX, mouseY, innerScrollBar);

                    int value = (int) ((innerScrollBar.getMaxValue(parent) / 100D) * percentage);

                    innerScrollBar.setValue(value);
                    updateScrollBarPositionBasedOnPercentage(parent);
                    if (parent.getContent() != null) {
                        parent.getContent().updateContentPositionBasedOnPercentage(parent, percentage);
                    }
                }
            });
        }
        if (getScrollBar() != null) {
            getScrollBar().startRenderable();
        }
    }

    private double getDragPercentage(double mouseX, double mouseY, ScrollBar innerScrollBar) {
        boolean isHorizontal = getOrientation() == ScrollOrientation.HORIZONTAL;
        int scrollBarDimensionRate = (isHorizontal ? getWidth() - innerScrollBar.getWidth() / 2 : getHeight() - innerScrollBar.getHeight() / 2) / 2;
        double mousePosition = isHorizontal ? mouseX : mouseY;
        return Mth.clamp((mousePosition / scrollBarDimensionRate) * 50, 0D, 100D);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.pose().pushPose();
        if (background != null) {
            if (centerBackground) {
                graphics.pose().translate((this.getWidth() - background.getWidth()) / 2F, (this.getHeight() - background.getHeight()) / 2F, getZ());
            }
            background.renderBase(graphics, mouseX, mouseY, delta);
        }
        graphics.pose().popPose();
        if (scrollBar != null) {
            scrollBar.renderBase(graphics, mouseX, mouseY, delta);
        }
    }

    public @Nullable ScrollBar getScrollBar() {
        return scrollBar;
    }

    public void setScrollBar(@Nullable ScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    public void setBackground(@Nullable IBackground<?> background) {
        this.background = background;
    }

    public @Nullable IBackground<?> getBackground() {
        return background;
    }

    public void removeBackground() {
        this.background = null;
    }

    public ScrollOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(ScrollOrientation orientation) {
        this.orientation = orientation;
    }

    public boolean isCenterBackground() {
        return centerBackground;
    }

    public void setCenterBackground(boolean centerBackground) {
        this.centerBackground = centerBackground;
    }

    public void scroll(ScrollPaneComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta) {
        if (scrollBar != null) {
            scrollBar.scroll(scrolledObject, screen, mouseX, mouseY, delta);
            updateScrollBarPositionBasedOnPercentage(scrolledObject);
        }
    }

    public void updateScrollBarPositionBasedOnPercentage(ScrollPaneComponent scrolledObject) {
        if (scrollBar != null) {
            int value = scrollBar.getValue();
            int maxValue = scrollBar.getMaxValue(scrolledObject);
            if (scrolledObject.getOrientation() == ScrollOrientation.HORIZONTAL) {
                scrollBar.setX((int) ((getWidth() - scrollBar.getWidth()) * ((double) value / (double) maxValue)));
            } else {
                scrollBar.setY((int) ((getHeight() - scrollBar.getHeight()) * ((double) value / (double) maxValue)));
            }
        }
    }

    @Override
    public void preformOnScrollEvent(double mouseX, double mouseY, double delta) {
        super.preformOnScrollEvent(mouseX, mouseY, delta);
    }
}
