package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.client.gui.component.AbstractSpriteComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractScrollComponent<T extends AbstractScrollComponent<T>> extends AbstractSpriteComponent<T> {

    protected static final LinkedList<ResourceLocation> DEFAULT_SPRITES = new LinkedList<>(List.of(
            ResourceLocation.withDefaultNamespace("widget/text_field"),
            ResourceLocation.withDefaultNamespace("widget/text_field_highlighted"),
            ResourceLocation.withDefaultNamespace("widget/scroller")
    ));
    private static final int INNER_PADDING = 4;
    private static final int SCROLL_BAR_WIDTH = 8;
    private double scrollAmount;
    private boolean scrolling;

    public AbstractScrollComponent(int x, int y, int width, int height) {
        this(DEFAULT_SPRITES, x, y, width, height);
    }

    public AbstractScrollComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height) {
        super(sprites, x, y, width, height);
    }

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (!this.isVisible()) {
            return false;
        }
        boolean bl = this.withinContentAreaPoint(mouseX, mouseY);
        boolean bl2 = this.scrollbarVisible() && mouseX >= (double) (this.getX() + getWidth()) && mouseX <= (double) (this.getX() + getWidth() + 8) && mouseY >= (double) this.getY() && mouseY < (double) (this.getY() + getHeight());
        if (bl2 && button == 0) {
            this.scrolling = true;
            return true;
        }
        return bl || bl2;
    }

    @Override
    public boolean preformOnMouseReleaseEvent(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.scrolling = false;
        }
        return super.preformOnMouseReleaseEvent(mouseX, mouseY, button);
    }

    @Override
    public boolean preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (!(this.isVisible() && this.isFocused() && this.scrolling)) {
            return false;
        }
        if (mouseY < (double)this.getY()) {
            this.setScrollAmount(0.0);
        } else if (mouseY > (double)(this.getY() + getHeight())) {
            this.setScrollAmount(this.getMaxScrollAmount());
        } else {
            int j = this.getScrollBarHeight();
            double h = Math.max(1, this.getMaxScrollAmount() / (getHeight() - j));
            this.setScrollAmount(this.scrollAmount + dragY * h);
        }
        return true;
    }

    @Override
    public boolean preformOnScrollEvent(double mouseX, double mouseY, double amountX, double amountY) {
        if (!this.isVisible()) {
            return false;
        }
        this.setScrollAmount(this.scrollAmount - amountY * this.scrollRate());
        return true;
    }

    @Override
    public boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        boolean bl = keyCode == 265;
        boolean bl2 = keyCode == 264;
        if (bl || bl2) {
            double d = this.scrollAmount;
            this.setScrollAmount(this.scrollAmount + (double)(bl ? -1 : 1) * this.scrollRate());
            if (d != this.scrollAmount) {
                return true;
            }
        }
        return super.preformOnKeyPressedEvent(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        guiGraphics.enableScissor(getTotalX() + innerPadding(), getTotalY() + innerPadding(), getTotalX() + getWidth() - innerPadding(), getTotalY() + getHeight() - innerPadding());
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0, -this.scrollAmount, 0.0);
        this.renderContents(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.pose().popPose();
        guiGraphics.disableScissor();
        this.renderDecorations(guiGraphics);
    }

    private int getScrollBarHeight() {
        return Mth.clamp((int)((float)(getHeight() * getHeight()) / (float)this.getContentHeight()), 32, getHeight());
    }

    protected void renderDecorations(GuiGraphics guiGraphics) {
        if (this.scrollbarVisible()) {
            this.renderScrollBar(guiGraphics);
        }
    }

    protected int innerPadding() {
        return 4;
    }

    protected int totalInnerPadding() {
        return this.innerPadding() * 2;
    }

    protected double scrollAmount() {
        return this.scrollAmount;
    }

    protected void setScrollAmount(double d) {
        this.scrollAmount = Mth.clamp(d, 0.0, this.getMaxScrollAmount());
    }

    protected int getMaxScrollAmount() {
        return Math.max(0, this.getContentHeight() - (getHeight() - 4));
    }

    private int getContentHeight() {
        return this.getInnerHeight() + 4;
    }

    protected void renderBackground(GuiGraphics guiGraphics) {
        ResourceLocation resourceLocation = isFocused() ? getSprite(1) : getSprite(0);
        guiGraphics.blitSprite(resourceLocation, 0, 0, this.getWidth(), this.getHeight());
    }

    private void renderScrollBar(GuiGraphics guiGraphics) {
        int i = this.getScrollBarHeight();
        int j = getWidth();
        int k = Math.max(0, (int)this.scrollAmount * (getHeight() - i) / this.getMaxScrollAmount());
        RenderSystem.enableBlend();
        guiGraphics.blitSprite(getSprite(2), j, k, 8, i);
        RenderSystem.disableBlend();
    }

    protected boolean withinContentAreaTopBottom(int i, int j) {
        return (double)j - this.scrollAmount >= 0D && (double)i - this.scrollAmount <= (double)(getHeight());
    }

    protected boolean withinContentAreaPoint(double mouseX, double mouseY) {
        return mouseX >= (double)this.getTotalX() && mouseX < (double)(this.getTotalX() + getWidth()) && mouseY >= (double)this.getTotalY() && mouseY < (double)(this.getTotalY() + getHeight());
    }

    protected boolean scrollbarVisible() {
        return this.getInnerHeight() > this.getHeight();
    }

    public int scrollbarWidth() {
        return 8;
    }

    protected abstract int getInnerHeight();

    protected abstract double scrollRate();

    protected abstract void renderContents(GuiGraphics var1, int var2, int var3, float var4);
}
