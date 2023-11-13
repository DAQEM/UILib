package com.daqem.uilib.client.gui.component.scroll;

import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollType;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.color.ColorManipulator;
import com.daqem.uilib.client.gui.component.AbstractNineSlicedComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class ScrollPaneComponent extends AbstractNineSlicedComponent<ScrollPaneComponent> {

    private ScrollOrientation orientation;
    private ScrollType scrollType = ScrollType.SMOOTH;
    private @Nullable ScrollContent content;
    private @Nullable ScrollBarWrapper scrollBarWrapper;
    private double scrollSpeed = 10D;

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation orientation) {
        this(texture, x, y, width, height, orientation, null, null);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation orientation, @Nullable ScrollContent content) {
        this(texture, x, y, width, height, orientation, content, null);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation orientation, @Nullable ScrollBarWrapper scrollBarWrapper) {
        this(texture, x, y, width, height, orientation, null, scrollBarWrapper);
    }

    public ScrollPaneComponent(ITexture texture, int x, int y, int width, int height, ScrollOrientation orientation, @Nullable ScrollContent content, @Nullable ScrollBarWrapper scrollBarWrapper) {
        super(texture, x, y, width, height, null, null, null);
        this.orientation = orientation;
        this.content = content;
        if (content != null) {
            content.setParent(this, false);
        }
        this.scrollBarWrapper = scrollBarWrapper;
        if (scrollBarWrapper != null) {
            scrollBarWrapper.setParent(this, false);
        }
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
            getScrollBar().startRenderable();
        }
        if (getContent() != null) {
            getContent().startRenderable();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        if (getScrollBar() != null) {
            setScrollBarLength();
            getScrollBar().renderBase(graphics, mouseX, mouseY, delta);
        }
        if (this.getContent() != null) {
            graphics.enableScissor(getTotalX(), getTotalY(), getTotalX() + getWidth(), getTotalY() + getHeight());
            this.getContent().renderBase(graphics, mouseX, mouseY, delta);
            graphics.disableScissor();
        }
    }

    private void setScrollBarLength() {
        if (getScrollBar() == null || getScrollBar().getScrollBar() == null) return;
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

    public void setScrollBar(@Nullable ScrollBarWrapper scrollBarWrapper) {
        this.scrollBarWrapper = scrollBarWrapper;
        if (scrollBarWrapper != null) {
            scrollBarWrapper.setParent(this, false);
        }
    }


    public @Nullable ScrollBarWrapper getScrollBar() {
        return scrollBarWrapper;
    }

    public void removeScrollBar() {
        this.scrollBarWrapper = null;
    }

    public @Nullable ScrollContent getContent() {
        return content;
    }

    public void setContent(@Nullable ScrollContent content) {
        this.content = content;
        if (content != null) {
            content.setParent(this, false);
        }
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

    public double getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(double scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    @Override
    public void preformOnClickEvent(double mouseX, double mouseY, int button) {
        super.preformOnClickEvent(mouseX, mouseY, button);
        if (getContent() != null) {
            getContent().preformOnClickEvent(mouseX, mouseY, button);
            getContent().getChildren().forEach(component -> component.preformOnClickEvent(mouseX - component.getX(), mouseY - component.getY(), button));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnClickEvent(mouseX - getX(), mouseY - getY(), button);
            if (getScrollBar().getScrollBar() != null) {
                getScrollBar().getScrollBar().preformOnClickEvent(mouseX - getX() - getScrollBar().getX(), mouseY - getY() - getScrollBar().getY(), button);
            }
        }
    }

    @Override
    public void preformOnHoverEvent(double mouseX, double mouseY, float delta) {
        super.preformOnHoverEvent(mouseX, mouseY, delta);
        if (getContent() != null) {
            getContent().preformOnHoverEvent(mouseX, mouseY, delta);
            getContent().getChildren().forEach(component -> component.preformOnHoverEvent(mouseX - component.getX(), mouseY - component.getY(), delta));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnHoverEvent(mouseX - getX(), mouseY - getY(), delta);
            if (getScrollBar().getScrollBar() != null) {
                getScrollBar().getScrollBar().preformOnHoverEvent(mouseX - getX() - getScrollBar().getX(), mouseY - getY() - getScrollBar().getY(), delta);
            }

        }
    }

    @Override
    public void preformOnScrollEvent(double mouseX, double mouseY, double delta) {
        super.preformOnScrollEvent(mouseX, mouseY, delta);
        if (getContent() != null) {
            getContent().preformOnScrollEvent(mouseX, mouseY, delta);
            getContent().getChildren().forEach(component -> component.preformOnScrollEvent(mouseX - component.getX(), mouseY - component.getY(), delta));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnScrollEvent(mouseX - getX(), mouseY - getY(), delta);
            if (getScrollBar().getScrollBar() != null) {
                getScrollBar().getScrollBar().preformOnScrollEvent(mouseX - getX() - getScrollBar().getX(), mouseY - getY() - getScrollBar().getY(), delta);
            }
        }
    }

    @Override
    public void preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        super.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
        if (getContent() != null) {
            getContent().preformOnDragEvent(mouseX, mouseY, button, dragX, dragY);
            getContent().getChildren().forEach(component -> component.preformOnDragEvent(mouseX - component.getX(), mouseY - component.getY(), button, dragX - component.getX(), dragY - component.getY()));
        }
        if (getScrollBar() != null) {
            getScrollBar().preformOnDragEvent(mouseX - getX(), mouseY - getY(), button, dragX, dragY);
            if (getScrollBar().getScrollBar() != null) {
                getScrollBar().getScrollBar().preformOnDragEvent(mouseX - getX() - getScrollBar().getX(), mouseY - getY() - getScrollBar().getY(), button, dragX, dragY);
            }
        }
    }
}
