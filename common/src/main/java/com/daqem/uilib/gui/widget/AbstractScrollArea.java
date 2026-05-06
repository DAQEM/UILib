package com.daqem.uilib.gui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public abstract class AbstractScrollArea extends AbstractWidget {
    public static final int SCROLLBAR_WIDTH = 6;
    public static final int SCROLLBAR_MIN_HEIGHT = 32;
    public static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("widget/scroller");
    public static final ResourceLocation SCROLLER_BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("widget/scroller_background");
    public final ScrollbarSettings scrollbarSettings;
    public double scrollAmount;
    public boolean scrolling;

    public AbstractScrollArea(int x, int y, int width, int height, Component message, ScrollbarSettings scrollbarSettings) {
        super(x, y, width, height, message);
        this.scrollbarSettings = scrollbarSettings;
    }

    public boolean mouseScrolled(double mx, double my, double scrollX, double scrollY) {
        if (!this.visible) {
            return false;
        } else {
            this.setScrollAmount(this.scrollAmount() - scrollY * this.scrollRate());
            return true;
        }
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling) {
            if (mouseY < (double)this.getY()) {
                this.setScrollAmount(0.0F);
            } else if (mouseY > (double)this.getBottom()) {
                this.setScrollAmount(this.maxScrollAmount());
            } else {
                double max = Math.max(1, this.maxScrollAmount());
                int barHeight = this.scrollerHeight();
                double yDragScale = Math.max(1.0F, max / (double)(this.height - barHeight));
                this.setScrollAmount(this.scrollAmount() + dragY * yDragScale);
            }

            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        this.scrolling = false;
    }

    public double scrollAmount() {
        return this.scrollAmount;
    }

    public void setScrollAmount(double scrollAmount) {
        this.scrollAmount = Mth.clamp(scrollAmount, (double)0.0F, (double)this.maxScrollAmount());
    }

    public boolean updateScrolling(double mouseX, double mouseY, int button) {
        this.scrolling = this.scrollable() && this.isValidClickButton(button) && this.isOverScrollbar(mouseX, mouseY);
        return this.scrolling;
    }

    protected boolean isOverScrollbar(double x, double y) {
        return x >= (double)this.scrollBarX() && x <= (double)(this.scrollBarX() + this.scrollbarWidth()) && y >= (double)this.getY() && y < (double)this.getBottom();
    }

    public void refreshScrollAmount() {
        this.setScrollAmount(this.scrollAmount);
    }

    public int maxScrollAmount() {
        return Math.max(0, this.contentHeight() - this.height);
    }

    protected boolean scrollable() {
        return this.maxScrollAmount() > 0;
    }

    public int scrollbarWidth() {
        return this.scrollbarSettings.scrollbarWidth();
    }

    protected int scrollerHeight() {
        return Mth.clamp((int)((float)(this.height * this.height) / (float)this.contentHeight()), SCROLLBAR_MIN_HEIGHT, this.height - 8);
    }

    protected int scrollBarX() {
        return this.getRight() - this.scrollbarWidth();
    }

    public int scrollBarY() {
        return this.maxScrollAmount() == 0 ? this.getY() : Math.max(this.getY(), (int)this.scrollAmount * (this.height - this.scrollerHeight()) / this.maxScrollAmount() + this.getY());
    }

    protected void extractScrollbar(GuiGraphics graphics, int mouseX, int mouseY) {
        int scrollbarX = this.scrollBarX();
        int scrollerHeight = this.scrollerHeight();
        int scrollerY = this.scrollBarY();
        if (!this.scrollable() && this.scrollbarSettings.disabledScrollerSprite() != null) {
            graphics.blitSprite(this.scrollbarSettings.backgroundSprite(), scrollbarX, this.getY(), this.scrollbarWidth(), this.getHeight());
            graphics.blitSprite(this.scrollbarSettings.disabledScrollerSprite(), scrollbarX, this.getY(), this.scrollbarWidth(), scrollerHeight);
        }

        if (this.scrollable()) {
            graphics.blitSprite(this.scrollbarSettings.backgroundSprite(), scrollbarX, this.getY(), this.scrollbarWidth(), this.getHeight());
            graphics.blitSprite(this.scrollbarSettings.scrollerSprite(), scrollbarX, scrollerY, this.scrollbarWidth(), scrollerHeight);
        }

    }

    protected abstract int contentHeight();

    protected double scrollRate() {
        return this.scrollbarSettings.scrollRate();
    }

    public static ScrollbarSettings defaultSettings(int scrollRate) {
        return new ScrollbarSettings(SCROLLER_SPRITE, null, SCROLLER_BACKGROUND_SPRITE, SCROLLBAR_WIDTH, SCROLLBAR_MIN_HEIGHT, scrollRate, true);
    }

    @OnlyIn(Dist.CLIENT)
    public record ScrollbarSettings(ResourceLocation scrollerSprite, @Nullable ResourceLocation disabledScrollerSprite, ResourceLocation backgroundSprite, int scrollbarWidth, int scrollbarMinHeight, int scrollRate, boolean resizingScrollbar) {
    }
}
