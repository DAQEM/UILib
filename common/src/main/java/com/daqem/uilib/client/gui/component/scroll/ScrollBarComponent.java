package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

public class ScrollBarComponent extends AbstractNineSlicedComponent<ScrollBarComponent> {

    private @Nullable ScrollBar scrollBar;
    private @Nullable IBackground<?> background;

    public ScrollBarComponent(int x, int y, int width, int height) {
        this(x, y, width, height, null, null, null, null);
    }

    public ScrollBarComponent(int x, int y, int width, int height, ScrollBar scrollBar) {
        this(x, y, width, height, null, null, null, scrollBar);
    }

    public ScrollBarComponent(int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<ScrollBarComponent> onClickEvent, @Nullable OnHoverEvent<ScrollBarComponent> onHoverEvent, @Nullable ScrollBar scrollBar) {
        super(null, x, y, width, height, text, onClickEvent, onHoverEvent);
        this.scrollBar = scrollBar;
        this.setZ(1);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.pose().pushPose();
        if (background != null) {
            graphics.pose().translate((this.getWidth() - background.getWidth()) / 2F, (this.getHeight() - background.getHeight()) / 2F, getZ());
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

    public void scroll(ScrollPaneComponent scrolledObject, Screen screen, double mouseX, double mouseY, double delta) {
        if (scrollBar != null) {
            scrollBar.scroll(scrolledObject, screen, mouseX, mouseY, delta);
        }
    }
}
