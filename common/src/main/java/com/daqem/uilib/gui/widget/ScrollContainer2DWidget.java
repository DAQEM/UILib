package com.daqem.uilib.gui.widget;

import com.daqem.uilib.api.IParent;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.widget.IWidget;
import com.daqem.uilib.mixin.AbstractScrollAreaAccessor;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractContainerWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.ScreenDirection;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ScrollContainer2DWidget extends AbstractContainerWidget implements IWidget, IParent {

    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("widget/scroller");
    private static final ResourceLocation SCROLLER_BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("widget/scroller_background");

    protected final List<IComponent> components = new ArrayList<>();
    private final int contentSpacing;
    private double horizontalScrollAmount;
    private boolean scrollingHorizontal;
    private boolean scrollingVertical;

    public ScrollContainer2DWidget(int width, int height, int contentSpacing) {
        super(0, 0, width, height, Component.empty());
        this.contentSpacing = contentSpacing;
        this.horizontalScrollAmount = 0.0;
    }

    public ScrollContainer2DWidget(int width, int height) {
        this(width, height, 0);
    }

    @Override
    protected int contentHeight() {
        if (components.isEmpty()) {
            return 0;
        }
        return this.components.stream()
                .mapToInt(IComponent::getHeight)
                .sum() + (getContentSpacing() * (this.components.size() - 1));
    }

    @Override
    protected double scrollRate() {
        return 10;
    }

    protected int contentWidth() {
        if (components.isEmpty()) {
            return 0;
        }
        return this.components.stream()
                .mapToInt(IComponent::getWidth)
                .max()
                .orElse(0);
    }

    protected int maxHorizontalScrollAmount() {
        return Math.max(0, this.contentWidth() - this.width);
    }

    protected boolean horizontalScrollbarVisible() {
        return this.maxHorizontalScrollAmount() > 0;
    }

    protected int scrollerWidth() {
        int adjW = this.width - (this.scrollbarVisible() ? 6 : 0);
        int contentW = this.contentWidth();
        if (contentW <= adjW) {
            return 0;
        }
        return Mth.clamp((int) ((float) (adjW * adjW) / contentW), 32, adjW - 8);
    }

    protected double horizontalScrollRate() {
        return 10.0;
    }

    public double horizontalScrollAmount() {
        return this.horizontalScrollAmount;
    }

    public void setHorizontalScrollAmount(double amount) {
        this.horizontalScrollAmount = Mth.clamp(amount, 0.0, this.maxHorizontalScrollAmount());
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (!this.visible) {
            return false;
        }
        boolean handled = false;
        if (scrollY != 0.0) {
            this.setScrollAmount(this.scrollAmount() - scrollY * this.scrollRate());
            handled = true;
        }
        if (scrollX != 0.0) {
            this.setHorizontalScrollAmount(this.horizontalScrollAmount() - scrollX * this.horizontalScrollRate());
            handled = true;
        }
        return handled;
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        if (!this.active || !this.visible) {
            return false;
        }
        boolean handled = false;
        if (this.isValidClickButton(event.buttonInfo())) {
            boolean onHBar = this.horizontalScrollbarVisible() &&
                    event.y() >= this.getBottom() - 6 &&
                    event.y() < this.getBottom() &&
                    event.x() >= this.getX() &&
                    event.x() < this.getRight();
            this.scrollingHorizontal = onHBar;

            boolean onVBar = !onHBar && this.scrollbarVisible() &&
                    event.x() >= this.scrollBarX() &&
                    event.x() < this.scrollBarX() + 6 &&
                    event.y() >= this.getY() &&
                    event.y() < this.getBottom();
            this.scrollingVertical = onVBar;

            if (onHBar || onVBar) {
                handled = true;
            } else {
                Optional<GuiEventListener> optional = this.getChildAt(event.x(), event.y());
                if (optional.isPresent()) {
                    GuiEventListener guiEventListener = optional.get();
                    if (guiEventListener.mouseClicked(event, bl)) {
                        this.setFocused(guiEventListener);
                        if (event.button() == 0) {
                            this.setDragging(true);
                        }
                        handled = true;
                    }
                } else {
                    this.onClick(event, bl);
                    handled = true;
                }
            }
        }
        return handled;
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dragX, double dragY) {
        if (this.scrollingVertical) {
            if (event.y() < this.getY()) {
                this.setScrollAmount(0.0);
            } else if (event.y() > this.getBottom()) {
                this.setScrollAmount(this.maxScrollAmount());
            } else {
                double d = Math.max(1, this.maxScrollAmount());
                int i = this.scrollerHeight();
                double e = Math.max(1.0, d / (this.height - i));
                this.setScrollAmount(this.scrollAmount() + dragY * e);
            }
            return true;
        } else if (this.scrollingHorizontal) {
            if (event.x() < this.getX()) {
                this.setHorizontalScrollAmount(0.0);
            } else if (event.x() > this.getRight()) {
                this.setHorizontalScrollAmount(this.maxHorizontalScrollAmount());
            } else {
                double d = Math.max(1, this.maxHorizontalScrollAmount());
                int i = this.scrollerWidth();
                double e = Math.max(1.0, d / (this.width - i));
                this.setHorizontalScrollAmount(this.horizontalScrollAmount() + dragX * e);
            }
            return true;
        } else {
            return super.mouseDragged(event, dragX, dragY);
        }
    }

    @Override
    public void onRelease(MouseButtonEvent mouseButtonEvent) {
        this.scrollingVertical = false;
        this.scrollingHorizontal = false;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        boolean vBar = this.scrollbarVisible();
        boolean hBar = this.horizontalScrollbarVisible();
        int availW = this.width - (vBar ? 6 : 0);
        int availH = this.height - (hBar ? 6 : 0);
        guiGraphics.enableScissor(this.getX(), this.getY(), this.getX() + availW, this.getY() + availH);

        int currentX = this.uilib$getParentX() - this.getX() - (int) this.horizontalScrollAmount();
        int currentY = this.uilib$getParentY() - this.getY() - (int) this.scrollAmount();
        for (int i = 0; i < this.components.size(); i++) {
            IComponent component = this.components.get(i);
            component.setX(currentX);
            component.setY(currentY);
            component.renderBase(guiGraphics, mouseX, mouseY, partialTick, availW, availH);
            currentY += component.getHeight();
            if (i < this.components.size() - 1) {
                currentY += getContentSpacing();
            }
        }

        guiGraphics.disableScissor();
        this.renderScrollbar(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderScrollbar(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Vertical scrollbar
        if (this.scrollbarVisible()) {
            int adjH = this.height - (this.horizontalScrollbarVisible() ? 6 : 0);
            int scrollBarX = this.scrollBarX();
            int scrollerHeight = this.scrollerHeight();
            int scrollBarY = this.scrollBarY();
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SCROLLER_BACKGROUND_SPRITE, scrollBarX, this.getY(), 6, adjH);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SCROLLER_SPRITE, scrollBarX, scrollBarY, 6, scrollerHeight);
            if (this.isOverScrollbar(mouseX, mouseY)) {
                guiGraphics.requestCursor(((AbstractScrollAreaAccessor) this).uilib$isScrolling() ? CursorTypes.RESIZE_NS : CursorTypes.POINTING_HAND);
            }
        }

        // Horizontal scrollbar
        if (this.horizontalScrollbarVisible()) {
            int adjW = this.width - (this.scrollbarVisible() ? 6 : 0);
            int bottom = this.getBottom();
            int hY = bottom - 6;
            int hX = this.getX();
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SCROLLER_BACKGROUND_SPRITE, hX, hY, adjW, 6);
            int scrollerW = this.scrollerWidth();
            double fracH = this.maxHorizontalScrollAmount() > 0 ? this.horizontalScrollAmount() / this.maxHorizontalScrollAmount() : 0.0;
            int scrollerX = (int) (hX + fracH * (adjW - scrollerW));
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SCROLLER_SPRITE, scrollerX, hY, scrollerW, 6);
            if (this.isOverScrollbar(mouseX, mouseY)) {
                guiGraphics.requestCursor(((AbstractScrollAreaAccessor) this).uilib$isScrolling() ? CursorTypes.RESIZE_NS : CursorTypes.POINTING_HAND);
            }
        }
    }

    @Override
    protected boolean isOverScrollbar(double d, double e) {
        boolean overVBar = this.scrollbarVisible() &&
                d >= this.scrollBarX() &&
                d < this.scrollBarX() + 6 &&
                e >= this.getY() &&
                e < this.getBottom();
        boolean overHBar = this.horizontalScrollbarVisible() &&
                e >= this.getBottom() - 6 &&
                e < this.getBottom() &&
                d >= this.getX() &&
                d < this.getRight();
        return overVBar || overHBar;
    }

    @Override
    protected int scrollerHeight() {
        int adjH = this.height - (this.horizontalScrollbarVisible() ? 6 : 0);
        int contentH = this.contentHeight();
        if (contentH <= adjH) {
            return 0;
        }
        return Mth.clamp((int) ((float) (adjH * adjH) / contentH), 32, adjH - 8);
    }

    @Override
    protected int scrollBarY() {
        int max = this.maxScrollAmount();
        if (max == 0) {
            return this.getY();
        }
        int adjH = this.height - (this.horizontalScrollbarVisible() ? 6 : 0);
        int h = this.scrollerHeight();
        return (int) (this.scrollAmount() * (adjH - h) / max) + this.getY();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    @Override
    public @NotNull ScreenRectangle getBorderForArrowNavigation(ScreenDirection direction) {
        return new ScreenRectangle(this.getX(), this.getY(), this.width, this.contentHeight());
    }

    @Override
    public void setFocused(@Nullable GuiEventListener focused) {
        super.setFocused(focused);
        if (focused != null && Minecraft.getInstance().getLastInputType().isKeyboard()) {
            ScreenRectangle screenRectangle = this.getRectangle();
            ScreenRectangle screenRectangle2 = focused.getRectangle();
            int i = screenRectangle2.top() - screenRectangle.top();
            int j = screenRectangle2.bottom() - screenRectangle.bottom();
            if (i < 0) {
                this.setScrollAmount(this.scrollAmount() + i - 14.0);
            } else if (j > 0) {
                this.setScrollAmount(this.scrollAmount() + j + 14.0);
            }
        }
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return getWidgets();
    }

    @Override
    public @NotNull Collection<? extends NarratableEntry> getNarratables() {
        return getWidgets();
    }

    @Override
    public List<IComponent> getComponents() {
        return this.components;
    }

    @Override
    public void addComponent(IComponent component) {
        this.components.add(component);
    }

    @Override
    public void addComponents(List<? extends IComponent> components) {
        this.components.addAll(components);
    }

    @Override
    public void removeComponent(IComponent component) {
        this.components.remove(component);
    }

    @Override
    public void removeComponents(List<? extends IComponent> components) {
        this.components.removeAll(components);
    }

    @Override
    public void clearComponents() {
        this.components.clear();
    }

    @Override
    public void clear() {
        clearComponents();
        clearOnlyWidgets();
    }

    @Override
    public List<IWidget> getWidgets() {
        return this.components.stream()
                .flatMap(component -> component.getAllWidgets().stream())
                .toList();
    }

    @Override
    public void addWidget(IWidget widget) {
        throw new UnsupportedOperationException("Cannot add a widget directly to ScrollContainer2DWidget. Add it to a component instead.");
    }

    @Override
    public void addWidgets(List<? extends IWidget> widgets) {
        throw new UnsupportedOperationException("Cannot add widgets directly to ScrollContainer2DWidget. Add them to a component instead.");
    }

    @Override
    public void removeWidget(IWidget widget) {
        throw new UnsupportedOperationException("Cannot remove a widget directly from ScrollContainer2DWidget. Remove it from a component instead.");
    }

    @Override
    public void removeWidgets(List<? extends IWidget> widgets) {
        throw new UnsupportedOperationException("Cannot remove widgets directly from ScrollContainer2DWidget. Remove them from a component instead.");
    }

    @Override
    public void clearOnlyWidgets() {
        throw new UnsupportedOperationException("Cannot clear widgets directly from ScrollContainer2DWidget. Clear them from components instead.");
    }

    public int getContentSpacing() {
        return contentSpacing;
    }
}