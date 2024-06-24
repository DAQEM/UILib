package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollType;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ScrollPanelComponent extends AbstractNineSlicedComponent<ScrollPanelComponent> {

    private ScrollOrientation scrollOrientation;
    private ScrollType scrollType = ScrollType.SMOOTH;

    private @Nullable ScrollContentComponent scrollContentComponent;
    private @Nullable ScrollBarComponent scrollBarComponent;

    private double scrollSpeed = 10D;

    public ScrollPanelComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation scrollOrientation) {
        this(texture, x, y, width, height, scrollOrientation, null, null);
    }

    public ScrollPanelComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation scrollOrientation, @Nullable ScrollContentComponent scrollContentComponent) {
        this(texture, x, y, width, height, scrollOrientation, scrollContentComponent, null);
    }

    public ScrollPanelComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation scrollOrientation, @Nullable ScrollBarComponent scrollBarComponent) {
        this(texture, x, y, width, height, scrollOrientation, null, scrollBarComponent);
    }

    public ScrollPanelComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation scrollOrientation, @Nullable ScrollContentComponent scrollContentComponent, @Nullable ScrollBarComponent scrollBarComponent) {
        super(texture, x, y, width, height);
        this.scrollOrientation = scrollOrientation;
        this.scrollContentComponent = scrollContentComponent;
        this.scrollBarComponent = scrollBarComponent;

        if (scrollContentComponent != null) scrollContentComponent.setParent(this, false);
        if (scrollBarComponent != null) scrollBarComponent.setParent(this, false);
    }

    // region Initialization

    @Override
    public void startRenderable() {
        super.startRenderable();

        setOnScrollEvent(this::handleScroll);

        getScrollBar().ifPresent(ScrollBarComponent::startRenderable);
        getScrollContentComponent().ifPresent(ScrollContentComponent::startRenderable);
    }

    private void handleScroll(ScrollPanelComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta) {
        getScrollBar().ifPresent(s -> s.scroll(scrolledObject, delta));
        getScrollContentComponent().ifPresent(s -> s.scroll(scrolledObject, delta));
    }

    // endregion

    // region Rendering

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        renderScrollbar(graphics, mouseX, mouseY, delta);
        renderScrollContent(graphics, mouseX, mouseY, delta);
    }

    private void renderScrollContent(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        getScrollContentComponent().ifPresent(scrollContentComponent -> {
            graphics.enableScissor(getTotalX(), getTotalY(), getTotalX() + getWidth(), getTotalY() + getHeight());
            scrollContentComponent.renderBase(graphics, mouseX, mouseY, delta);
            graphics.disableScissor();
        });
    }

    private void renderScrollbar(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        getScrollBar().ifPresent(scrollBarComponent -> {
            setScrollWheelLength(scrollBarComponent);
            scrollBarComponent.renderBase(graphics, mouseX, mouseY, delta);
        });
    }

    // endregion

    // region Getters and Setters

    private void setScrollWheelLength(ScrollBarComponent scrollBarComponent) {
        int contentSize = getScrollContentComponent().map(scrollContentComponent -> scrollContentComponent.getOrientation().isHorizontal() ? scrollContentComponent.getWidth() : scrollContentComponent.getHeight()).orElse(0);
        scrollBarComponent.setScrollWheelLength(contentSize);
    }

    public int getOffScreenContentLength() {
        return getScrollContentComponent().map(scrollContentComponent -> scrollContentComponent.getOrientation().isHorizontal() ? scrollContentComponent.getWidth() - getWidth() : scrollContentComponent.getHeight() - getHeight()).orElse(0);
    }

    public void setScrollBar(@Nullable ScrollBarComponent scrollBarComponent) {
        this.scrollBarComponent = scrollBarComponent;
        if (scrollBarComponent != null) {
            scrollBarComponent.setParent(this, false);
        }
    }

    public Optional<ScrollBarComponent> getScrollBar() {
        return Optional.ofNullable(scrollBarComponent);
    }

    public void removeScrollBar() {
        this.scrollBarComponent = null;
    }

    public Optional<ScrollContentComponent> getScrollContentComponent() {
        return Optional.ofNullable(scrollContentComponent);
    }

    public void setScrollContentComponent(@Nullable ScrollContentComponent scrollContentComponent) {
        this.scrollContentComponent = scrollContentComponent;
        if (scrollContentComponent != null) {
            scrollContentComponent.setParent(this, false);
        }
    }

    public void removeScrollContentComponent() {
        this.scrollContentComponent = null;
    }

    public ScrollOrientation getScrollOrientation() {
        return scrollOrientation;
    }

    public void setScrollOrientation(ScrollOrientation scrollOrientation) {
        this.scrollOrientation = scrollOrientation;
    }

    public ScrollType getScrollType() {
        return scrollType;
    }

    public void setScrollType(ScrollType scrollType) {
        this.scrollType = scrollType;
    }

    public double getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(double scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    // endregion

    // region Event Pass Through

    @Override
    public void preformOnClickEvent(double mouseX, double mouseY, int button) {
        super.preformOnClickEvent(mouseX, mouseY, button);
        getScrollContentComponent().ifPresent(scrollContentComponent -> {
            scrollContentComponent.preformOnClickEvent(mouseX, mouseY, button);
            if (this.isTotalHovered(mouseX, mouseY)) {
                scrollContentComponent.getChildren().forEach(component -> component.preformOnClickEvent(mouseX, mouseY, button));
            }
        });
        getScrollBar().ifPresent(scrollBarComponent -> {
            scrollBarComponent.preformOnClickEvent(mouseX, mouseY, button);
            scrollBarComponent.getScrollWheel().ifPresent(scrollWheelComponent -> scrollWheelComponent.preformOnClickEvent(mouseX, mouseY, button));
        });
    }

    @Override
    public void preformOnHoverEvent(double mouseX, double mouseY, float delta) {
        super.preformOnHoverEvent(mouseX, mouseY, delta);
        getScrollContentComponent().ifPresent(scrollContentComponent -> {
            scrollContentComponent.preformOnHoverEvent(mouseX, mouseY, delta);
            scrollContentComponent.getChildren().forEach(component -> component.preformOnHoverEvent(mouseX - component.getX(), mouseY - component.getY(), delta));
        });
        getScrollBar().ifPresent(scrollBarComponent -> {
            scrollBarComponent.preformOnHoverEvent(mouseX - getX(), mouseY - getY(), delta);
            scrollBarComponent.getScrollWheel().ifPresent(scrollWheelComponent -> {
                scrollWheelComponent.preformOnHoverEvent(mouseX - getX() - scrollWheelComponent.getX(), mouseY - getY() - scrollWheelComponent.getY(), delta);
            });
        });
    }

    @Override
    public void preformOnScrollEvent(double mouseX, double mouseY, double delta) {
        super.preformOnScrollEvent(mouseX, mouseY, delta);
        getScrollContentComponent().ifPresent(scrollContentComponent -> {
            scrollContentComponent.preformOnScrollEvent(mouseX, mouseY, delta);
            scrollContentComponent.getChildren().forEach(component -> component.preformOnScrollEvent(mouseX - component.getX(), mouseY - component.getY(), delta));
        });
        getScrollBar().ifPresent(scrollBarComponent -> {
            scrollBarComponent.preformOnScrollEvent(mouseX - getX(), mouseY - getY(), delta);
            scrollBarComponent.getScrollWheel().ifPresent(scrollWheelComponent -> {
                scrollWheelComponent.preformOnScrollEvent(mouseX - getX() - scrollWheelComponent.getX(), mouseY - getY() - scrollWheelComponent.getY(), delta);
            });
        });
    }

    @Override
    public void preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        super.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
        getScrollContentComponent().ifPresent(scrollContentComponent -> {
            scrollContentComponent.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
            scrollContentComponent.getChildren().forEach(component -> component.preformOnDragEvent(mouseX - component.getX(), mouseY - component.getY(), button, dragX - component.getX(), dragY - component.getY()));
        });
        getScrollBar().ifPresent(scrollBarComponent -> {
            scrollBarComponent.preformOnDragEvent(mouseX - getX(), mouseY - getY(), button, dragX, dragY);
            scrollBarComponent.getScrollWheel().ifPresent(scrollWheelComponent -> {
                scrollWheelComponent.preformOnDragEvent(mouseX - getX() - scrollWheelComponent.getX(), mouseY - getY() - scrollWheelComponent.getY(), button, dragX, dragY);
            });
        });
    }

    // endregion
}
