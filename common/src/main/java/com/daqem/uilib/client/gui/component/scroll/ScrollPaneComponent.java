package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class ScrollPaneComponent extends AbstractNineSlicedComponent<ScrollPaneComponent> {

    private @Nullable ScrollContent content;
    private @Nullable ScrollBarComponent scrollBarComponent;

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height) {
        this(texture, x, y, width, height, null, null, null, null);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollContent content) {
        this(texture, x, y, width, height, null, null, null, content);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<ScrollPaneComponent> onClickEvent, @Nullable OnHoverEvent<ScrollPaneComponent> onHoverEvent, ScrollContent content) {
        super(texture, x, y, width, height, text, onClickEvent, onHoverEvent);
        this.content = content;
    }

    @Override
    public void startRenderable() {
        super.startRenderable();
        setOnScrollEvent((scrolledObject, screen, mouseX, mouseY, delta) -> {
            if (scrollBarComponent != null) {
                scrollBarComponent.scroll(scrolledObject, screen, mouseX, mouseY, delta);
            }
        });
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        if (scrollBarComponent != null) {
            scrollBarComponent.renderBase(graphics, mouseX, mouseY, delta);
        }
    }

    public void setScrollBar(@Nullable ScrollBarComponent scrollBarComponent) {
        this.scrollBarComponent = scrollBarComponent;
    }


    public @Nullable ScrollBarComponent getScrollBar() {
        return scrollBarComponent;
    }

    public void removeScrollBar() {
        this.scrollBarComponent = null;
    }

    public @Nullable ScrollContent getContent() {
        return content;
    }

    public void setContent(@Nullable ScrollContent content) {
        this.content = content;
    }
}
