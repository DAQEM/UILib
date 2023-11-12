package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;
import com.daqem.uilib.api.client.gui.component.event.OnHoverEvent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollType;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public class ScrollPaneComponent extends AbstractNineSlicedComponent<ScrollPaneComponent> {

    private ScrollOrientation orientation;
    private ScrollType scrollType = ScrollType.SMOOTH;
    private @Nullable ScrollContent content;
    private @Nullable ScrollBarComponent scrollBarComponent;

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height) {
        this(texture, x, y, width, height, null, null, null, null, null);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation orientation) {
        this(texture, x, y, width, height, null, null, null, null, orientation);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollContent content, ScrollOrientation orientation) {
        this(texture, x, y, width, height, null, null, null, content, orientation);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, @Nullable IText<?> text, @Nullable OnClickEvent<ScrollPaneComponent> onClickEvent, @Nullable OnHoverEvent<ScrollPaneComponent> onHoverEvent, @Nullable ScrollContent content, ScrollOrientation orientation) {
        super(texture, x, y, width, height, text, onClickEvent, onHoverEvent);
        this.content = content;
        this.orientation = orientation;
    }

    @Override
    public void startRenderable() {
        super.startRenderable();
        setOnScrollEvent((scrolledObject, screen, mouseX, mouseY, delta) -> {
            if (getScrollBar() != null) {
                getScrollBar().scroll(scrolledObject, screen, mouseX, mouseY, delta);
            }
            if (getContent() != null) {
                getContent().scroll(scrolledObject, screen, mouseX, mouseY, delta);
            }
        });
        if (getScrollBar() != null) {
            getScrollBar().setOnScrollEvent((scrolledObject, screen, mouseX, mouseY, delta) -> {
                if (getScrollBar() != null) {
                    getScrollBar().scroll(this, screen, mouseX, mouseY, delta);
                }
                if (getContent() != null) {
                    getContent().scroll(this, screen, mouseX, mouseY, delta);
                }
            });
            getScrollBar().setOnDragEvent((draggedObject, screen, mouseX, mouseY, button, dragX, dragY) -> {
                mouseX = mouseX - getScrollBar().getX();
                mouseY = mouseY - getScrollBar().getY();
                double percentage =  getOrientation() == ScrollOrientation.HORIZONTAL ? mouseX / (double) getScrollBar().getWidth() : mouseY / (double) getScrollBar().getHeight();
                //TODO working here
            });
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        if (getScrollBar() != null) {
            if (getScrollBar().getScrollBar() != null) {
                if (getOrientation() == ScrollOrientation.HORIZONTAL) {
                    int totalBarWidth = getScrollBar().getWidth();
                    if (getContent() != null) {
                        int contentWidth = getContent().getWidth();
                        int width = (int) ((float) totalBarWidth / (float) contentWidth * (float) totalBarWidth);
                        if (width > totalBarWidth) {
                            width = totalBarWidth;
                        }
                        getScrollBar().getScrollBar().setWidth(width);
                    }
                } else {
                    int totalBarHeight = getScrollBar().getHeight();
                    if (getContent() != null) {
                        int contentHeight = getContent().getHeight();
                        int height = (int) ((float) totalBarHeight / (float) contentHeight * (float) totalBarHeight);
                        if (height > totalBarHeight) {
                            height = totalBarHeight;
                            getScrollBar().getScrollBar().setColorManipulator(new ColorManipulator(.75F, .75F, .75F));
                        }
                        getScrollBar().getScrollBar().setHeight(height);
                    }
                }
            }
            getScrollBar().renderBase(graphics, mouseX, mouseY, delta);
        }
        if (this.getContent() != null) {
            graphics.enableScissor(getTotalX(), getTotalY(), getTotalX() + getWidth(), getTotalY() + getHeight());
            this.getContent().renderBase(graphics, mouseX, mouseY, delta);
            graphics.disableScissor();
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

    public ScrollOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(ScrollOrientation orientation) {
        this.orientation = orientation;
    }

    public ScrollType getScrollType() {
        return scrollType;
    }

    public void setScrollType(ScrollType scrollType) {
        this.scrollType = scrollType;
    }

    @Override
    public void preformOnClickEvent(double mouseX, double mouseY, int button) {
        super.preformOnClickEvent(mouseX, mouseY, button);
        if (getContent() != null) {
            getContent().preformOnClickEvent(mouseX, mouseY, button);
            getContent().getComponents().forEach(component -> component.preformOnClickEvent(mouseX - component.getX(), mouseY - component.getY(), button));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnClickEvent(mouseX, mouseY, button);
        }
        if (getScrollBar().getScrollBar() != null) {
            getScrollBar().getScrollBar().preformOnClickEvent(mouseX, mouseY, button);
        }
    }

    @Override
    public void preformOnHoverEvent(double mouseX, double mouseY, float delta) {
        super.preformOnHoverEvent(mouseX, mouseY, delta);
        if (getContent() != null) {
            getContent().preformOnHoverEvent(mouseX, mouseY, delta);
            getContent().getComponents().forEach(component -> component.preformOnHoverEvent(mouseX - component.getX(), mouseY - component.getY(), delta));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnHoverEvent(mouseX, mouseY, delta);
        }
        if (getScrollBar().getScrollBar() != null) {
            getScrollBar().getScrollBar().preformOnHoverEvent(mouseX, mouseY, delta);
        }
    }

    @Override
    public void preformOnScrollEvent(double mouseX, double mouseY, double delta) {
        super.preformOnScrollEvent(mouseX, mouseY, delta);
        if (getContent() != null) {
            getContent().preformOnScrollEvent(mouseX, mouseY, delta);
            getContent().getComponents().forEach(component -> component.preformOnScrollEvent(mouseX - component.getX(), mouseY - component.getY(), delta));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnScrollEvent(mouseX, mouseY, delta);
        }
        if (getScrollBar().getScrollBar() != null) {
            getScrollBar().getScrollBar().preformOnScrollEvent(mouseX, mouseY, delta);
        }
    }

    @Override
    public void preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        super.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
        if (getContent() != null) {
            getContent().preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
            getContent().getComponents().forEach(component -> component.preformOnDragEvent(mouseX - component.getX(), mouseY - component.getY(), button, dragX - component.getX(), dragY - component.getY()));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
        }
        if (getScrollBar().getScrollBar() != null) {
            getScrollBar().getScrollBar().preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
        }
    }
}
