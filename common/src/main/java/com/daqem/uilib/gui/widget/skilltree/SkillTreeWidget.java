package com.daqem.uilib.gui.widget.skilltree;

import com.daqem.uilib.api.IParent;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.widget.IWidget;
import com.daqem.uilib.api.widget.skilltree.ISkillTreeWidget;
import com.daqem.uilib.gui.component.skilltree.SkillTreeMovingComponent;
import com.daqem.uilib.gui.widget.ScrollContainer2DWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.ScreenDirection;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.MouseButtonEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SkillTreeWidget extends ScrollContainer2DWidget implements IWidget, IParent, ISkillTreeWidget {

    private boolean hasDragged; // Tracks if a drag occurred
    private double clickStartX; // X-coordinate where mouse was pressed
    private double clickStartY; // Y-coordinate where mouse was pressed
    private static final double DRAG_THRESHOLD = 2.0; // Pixels to consider a drag

    public SkillTreeWidget(int width, int height) {
        super(width, height);
    }

    @Override
    protected int contentHeight() {
        return this.components.isEmpty() ? 0 : this.components.getFirst().getHeight();
    }

    @Override
    protected int contentWidth() {
        return this.components.isEmpty() ? 0 : this.components.getFirst().getWidth();
    }

    @Override
    public void addComponent(IComponent component) {
        this.components.clear();
        this.components.add(component);
    }

    @Override
    public void addComponents(List<? extends IComponent> components) {
        if (components.size() > 1) {
            throw new IllegalArgumentException("SkillTreeWidget can only hold one component.");
        }
        if (!components.isEmpty()) {
            this.addComponent(components.getFirst());
        }
    }

    @Override
    public void removeComponent(IComponent component) {
        if (!this.components.isEmpty() && this.components.getFirst() == component) {
            this.components.clear();
        }
    }

    @Override
    public void removeComponents(List<? extends IComponent> components) {
        if (!this.components.isEmpty() && components.contains(this.components.getFirst())) {
            this.components.clear();
        }
    }

    @Override
    public void clearComponents() {
        this.components.clear();
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.components.isEmpty()) {
            return;
        }
        guiGraphics.enableScissor(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
        IComponent component = this.components.getFirst();
        int offsetX = (int) this.horizontalScrollAmount();
        int offsetY = (int) this.scrollAmount();
        component.setX(this.getX() - uilib$getParentX() - offsetX);
        component.setY(this.getY() - uilib$getParentY() - offsetY);
        component.renderBase(guiGraphics, mouseX, mouseY, partialTick, this.getWidth(), this.getHeight());
        guiGraphics.disableScissor();
    }

    @Override
    protected void renderScrollbar(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // No scrollbars
    }

    @Override
    protected boolean scrollbarVisible() {
        return false;
    }

    @Override
    protected boolean horizontalScrollbarVisible() {
        return false;
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        if (!this.active || !this.visible) {
            return false;
        }
        if (this.isValidClickButton(event.buttonInfo())) {
            this.hasDragged = false; // Reset drag state
            this.clickStartX = event.x(); // Store click start position
            this.clickStartY = event.y();
            this.setDragging(true); // Enable dragging
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dragX, double dragY) {
        if (this.isValidClickButton(event.buttonInfo()) && this.isDragging()) {
            // Check if movement exceeds drag threshold
            double deltaX = Math.abs(event.x() - this.clickStartX);
            double deltaY = Math.abs(event.y() - this.clickStartY);
            if (deltaX > DRAG_THRESHOLD || deltaY > DRAG_THRESHOLD) {
                this.hasDragged = true;
            }
            double newHorizontal = this.horizontalScrollAmount() - dragX;
            this.setHorizontalScrollAmount(newHorizontal);
            double newVertical = this.scrollAmount() - dragY;
            this.setScrollAmount(newVertical);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        super.mouseReleased(event);
        if (this.isValidClickButton(event.buttonInfo())) {
            this.setDragging(false);
            if (!this.hasDragged) {
                // Only process click if no drag occurred
                Optional<GuiEventListener> optional = this.getChildAt(event.x(), event.y());
                if (optional.isPresent()) {
                    GuiEventListener guiEventListener = optional.get();
                    boolean handled = guiEventListener.mouseClicked(event, false);
                    if (handled) {
                        this.setFocused(guiEventListener);
                        return true;
                    }
                }
                this.onClick(event, false);
                return true;
            }
            if (this.getFocused() != null) {
                return this.getFocused().mouseReleased(event);
            }
        }
        return false;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        // No narration for skill tree widget
    }

    @Override
    public @NotNull ScreenRectangle getBorderForArrowNavigation(ScreenDirection direction) {
        return new ScreenRectangle(this.getX(), this.getY(), this.contentWidth(), this.contentHeight());
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
        return this.components.isEmpty() ? List.of() : this.components.getFirst().getAllWidgets();
    }

    @Override
    public @NotNull Collection<? extends NarratableEntry> getNarratables() {
        return this.components.isEmpty() ? List.of() : this.components.getFirst().getAllWidgets();
    }

    @Override
    public List<IComponent> getComponents() {
        return new ArrayList<>(this.components);
    }

    @Override
    public void clear() {
        this.clearComponents();
    }

    @Override
    public List<IWidget> getWidgets() {
        return this.components.isEmpty() ? List.of() : this.components.getFirst().getAllWidgets();
    }

    @Override
    public void addWidget(IWidget widget) {
        throw new UnsupportedOperationException("Cannot add a widget directly to SkillTreeWidget. Add it to the component instead.");
    }

    @Override
    public void addWidgets(List<? extends IWidget> widgets) {
        throw new UnsupportedOperationException("Cannot add widgets directly to SkillTreeWidget. Add them to the component instead.");
    }

    @Override
    public void removeWidget(IWidget widget) {
        throw new UnsupportedOperationException("Cannot remove a widget directly from SkillTreeWidget. Remove it from the component instead.");
    }

    @Override
    public void removeWidgets(List<? extends IWidget> widgets) {
        throw new UnsupportedOperationException("Cannot remove widgets directly from SkillTreeWidget. Remove them from the component instead.");
    }

    @Override
    public void clearOnlyWidgets() {
        throw new UnsupportedOperationException("Cannot clear widgets directly from SkillTreeWidget. Clear them from the component instead.");
    }

    public int getContentSpacing() {
        return 0;
    }

    @Override
    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (this.isMouseOver(mouseX, mouseY)) {
            if (!this.components.isEmpty()) {
                if (this.components.getFirst() instanceof SkillTreeMovingComponent skillTreeMovingComponent) {
                    skillTreeMovingComponent.renderTooltips(guiGraphics, mouseX, mouseY);
                }
            }
        }
    }
}