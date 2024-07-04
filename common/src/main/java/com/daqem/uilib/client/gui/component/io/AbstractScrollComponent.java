package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.client.gui.component.AbstractSpriteComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;

public abstract class AbstractScrollComponent<T extends AbstractScrollComponent<T>> extends AbstractSpriteComponent<T> {

    private static final int INNER_PADDING = 4;
    private static final int SCROLL_BAR_WIDTH = 8;
    private double scrollAmount;
    private boolean scrolling;

    public AbstractScrollComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height) {
        super(sprites, x, y, width, height);
    }

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (!this.isVisible()) {
            return false;
        }
        boolean bl = this.withinContentAreaPoint(mouseX, mouseY);
        boolean bl2 = this.scrollbarVisible() && mouseX >= (double) (this.getTotalX() + getWidth()) && mouseX <= (double) (this.getTotalX() + getWidth() + SCROLL_BAR_WIDTH) && mouseY >= (double) this.getTotalY() && mouseY < (double) (this.getTotalY() + getHeight());
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
        if (!(this.isVisible() && this.scrolling)) {
            return false;
        }
        if (mouseY < (double)this.getTotalY()) {
            this.setScrollAmount(0.0);
        } else if (mouseY > (double)(this.getTotalY() + getHeight())) {
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
        if (!this.isVisible() || !isFocused() || !this.withinContentAreaPoint(mouseX, mouseY)) {
            return false;
        }
        this.setScrollAmount(this.scrollAmount - amountY * this.scrollRate());
        return true;
    }

    @Override
    public boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        boolean bl = keyCode == GLFW.GLFW_KEY_UP;
        boolean bl2 = keyCode == GLFW.GLFW_KEY_DOWN;
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
        return INNER_PADDING;
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
        return Math.max(0, this.getContentHeight() - (getHeight() - INNER_PADDING));
    }

    private int getContentHeight() {
        return this.getInnerHeight() + INNER_PADDING;
    }

    protected void renderBackground(GuiGraphics guiGraphics) {
        ResourceLocation resourceLocation = getBackgroundSprite();
        guiGraphics.blitSprite(resourceLocation, 0, 0, this.getWidth(), this.getHeight());
    }

    protected abstract ResourceLocation getBackgroundSprite();

    private void renderScrollBar(GuiGraphics guiGraphics) {
        int i = this.getScrollBarHeight();
        int j = getWidth();
        int k = Math.max(0, (int)this.scrollAmount * (getHeight() - i) / this.getMaxScrollAmount());
        RenderSystem.enableBlend();
        guiGraphics.blitSprite(getScrollWheelSprite(), j, k, SCROLL_BAR_WIDTH, i);
        RenderSystem.disableBlend();
    }

    protected abstract ResourceLocation getScrollWheelSprite();

    protected boolean withinContentAreaTopBottom(int i, int j) {
        return (double)j - this.scrollAmount >= 0D && (double)i - this.scrollAmount <= (double)(getHeight());
    }

    protected boolean withinContentAreaPoint(double mouseX, double mouseY) {
        return mouseX >= (double)this.getTotalX() && mouseX < (double)(this.getTotalX() + getWidth()) && mouseY >= (double)this.getTotalY() && mouseY < (double)(this.getTotalY() + getHeight());
    }

    protected boolean scrollbarVisible() {
        return this.getInnerHeight() > this.getHeight();
    }

    public int getScrollbarWidth() {
        return SCROLL_BAR_WIDTH;
    }

    protected abstract int getInnerHeight();

    protected abstract double scrollRate();

    protected abstract void renderContents(GuiGraphics var1, int var2, int var3, float var4);
}
