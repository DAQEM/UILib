package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ScrollBarComponent extends AbstractNineSlicedComponent<ScrollBarComponent> {

    private @Nullable ScrollWheelComponent scrollWheelComponent;
    private @Nullable IBackground<?> background;
    private ScrollOrientation orientation;
    private boolean centerBackground = true;

    public ScrollBarComponent(int x, int y, int width, int height, ScrollOrientation orientation) {
        this(x, y, width, height, orientation, new ScrollWheelComponent(Textures.SCROLL_WHEEL, 0, 0, orientation.isHorizontal() ? width : height));
    }

    public ScrollBarComponent(int x, int y, int width, int height, ScrollOrientation orientation, @Nullable ScrollWheelComponent scrollWheelComponent) {
        super(null, x, y, width, height);
        this.scrollWheelComponent = scrollWheelComponent;
        this.orientation = orientation;
        this.background = Backgrounds.getSolidScrollBarBackground(this);
        this.setZ(1);
    }

    @Override
    public void startRenderable() {
        super.startRenderable();

        setOnScrollEvent(this::handleScroll);
        setOnDragEvent(this::handleDrag);
        getScrollWheel().ifPresent(ScrollWheelComponent::startRenderable);
    }

    private void handleScroll(ScrollBarComponent scrollBarComponent, Screen screen, double mouseX, double mouseY, double delta) {
        if (this.getParent() instanceof ScrollPanelComponent parent) {
            scroll(parent, delta);
            parent.getScrollContentComponent().ifPresent(s -> s.scroll(parent, delta));
        }
    }

    private void handleDrag(ScrollBarComponent scrollBarComponent, Screen screen, double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.getParent() instanceof ScrollPanelComponent parent) {
            getScrollWheel().ifPresent(scrollWheelComponent -> {
                double dragPercentage = getDragPercentage(mouseX, mouseY, scrollWheelComponent);
                int scrollValue = (int) ((scrollWheelComponent.getMaxValue(parent) / 100D) * dragPercentage);
                scrollWheelComponent.setScrollValue(scrollValue);
                this.updateScrollBarPositionBasedOnPercentage(parent);
                parent.getScrollContentComponent().ifPresent(s -> s.updateContentPositionBasedOnPercentage(parent, dragPercentage));
            });
        }
    }

    private double getDragPercentage(double mouseX, double mouseY, ScrollWheelComponent scrollWheelComponent) {
        boolean isHorizontal = getOrientation().isHorizontal();
        int scrollWheelDimension = scrollWheelComponent.getDimension(isHorizontal);
        int scrollBarLength = getDimension(isHorizontal);
        double offset = scrollWheelDimension / 2.0;
        double mousePosition = isHorizontal ? mouseX : mouseY;
        double newPosition = mousePosition - offset;

        if (newPosition < 0) {
            newPosition = 0;
        }
        if (newPosition > scrollBarLength - scrollWheelDimension) {
            newPosition = scrollBarLength - scrollWheelDimension;
        }
        return Mth.clamp((newPosition / (scrollBarLength - scrollWheelDimension)) * 100, 0D, 100D);
    }

    private int getDimension(boolean isHorizontal) {
        return isHorizontal ? getWidth() : getHeight();
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
        if (scrollWheelComponent != null) {
            scrollWheelComponent.renderBase(graphics, mouseX, mouseY, delta);
        }
    }

    public Optional<ScrollWheelComponent> getScrollWheel() {
        return Optional.ofNullable(scrollWheelComponent);
    }

    public void setScrollBar(@Nullable ScrollWheelComponent scrollWheelComponent) {
        this.scrollWheelComponent = scrollWheelComponent;
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

    public void scroll(ScrollPanelComponent scrolledObject, double delta) {
        getScrollWheel().ifPresent(scrollWheelComponent -> {
            scrollWheelComponent.scroll(scrolledObject, delta);
            this.updateScrollBarPositionBasedOnPercentage(scrolledObject);
        });

    }

    public void updateScrollBarPositionBasedOnPercentage(ScrollPanelComponent scrolledObject) {
        getScrollWheel().ifPresent(scrollWheelComponent -> {
            int value = scrollWheelComponent.getScrollValue();
            int maxValue = scrollWheelComponent.getMaxValue(scrolledObject);
            if (scrolledObject.getScrollOrientation().isHorizontal()) {
                scrollWheelComponent.setX((int) ((getWidth() - scrollWheelComponent.getWidth()) * ((double) value / (double) maxValue)));
            } else {
                scrollWheelComponent.setY((int) ((getHeight() - scrollWheelComponent.getHeight()) * ((double) value / (double) maxValue)));
            }
        });
    }

    @Override
    public void preformOnScrollEvent(double mouseX, double mouseY, double delta) {
        super.preformOnScrollEvent(mouseX, mouseY, delta);
    }

    public void setScrollWheelLength(int contentLength) {
        getScrollWheel().ifPresent(scrollWheelComponent -> {
            boolean isHorizontal = getOrientation().isHorizontal();

            int totalBarDimension = isHorizontal ? getWidth() : getHeight();
            int dimension = (int) ((float) totalBarDimension / (float)contentLength * (float)totalBarDimension);

            if (dimension >= totalBarDimension) {
                dimension = totalBarDimension;
                scrollWheelComponent.setColorManipulator(ColorManipulator.GRAY);
            } else {
                scrollWheelComponent.setColorManipulator(ColorManipulator.WHITE);
            }

            if (isHorizontal) {
                scrollWheelComponent.setWidth(dimension);
            } else {
                scrollWheelComponent.setHeight(dimension);
            }
        });
    }
}
