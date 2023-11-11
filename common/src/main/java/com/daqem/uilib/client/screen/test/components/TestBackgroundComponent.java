package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.background.NineSlicedBackground;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollBar;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollContent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;

import java.util.ArrayList;
import java.util.List;

public class TestBackgroundComponent extends TextureComponent {

    private final TestLeftTabs leftTabs = new TestLeftTabs();
    private final TestRightTabs rightTabs = new TestRightTabs();

    private final ScrollPaneComponent scrollPane;
    private final ScrollBarComponent scrollBar;

    public TestBackgroundComponent() {
        super(
                new Texture(UILibClient.getId("textures/jobs_screen.png"),
                        0, 0, 326, 166, 362),
                0, 0, 326, 166);
        this.scrollPane = new ScrollPaneComponent(Textures.SCROLL_PANE, 7, 15, 116, 140);
        this.scrollBar = new ScrollBarComponent(this.scrollPane.getWidth() + 4, 1, 12, 138);
    }

    @Override
    public void startRenderable() {
        this.center();
        this.addChildren(getAllTabs());
        NineSlicedBackground scrollBarBackground = Backgrounds.getScrollBarBackground(scrollBar.getWidth() + 2, scrollBar.getHeight() + 2);
        scrollBar.setBackground(scrollBarBackground);
        ScrollContent content = new ScrollContent(1, 1, 114, 138, 0, ScrollOrientation.VERTICAL);
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        content.addComponent(new ButtonComponent(Textures.BUTTON, 0, 0, 112, 20));
        scrollBar.setScrollBar(new ScrollBar(Textures.SCROLL_BAR, 0, 0, scrollBar.getWidth(), 10, ScrollOrientation.VERTICAL, content));
        scrollPane.setScrollBar(scrollBar);
        scrollPane.setContent(content);
        this.addChild(scrollPane);
        super.startRenderable();
    }

    public TestLeftTabs getLeftTabs() {
        return leftTabs;
    }

    public TestRightTabs getRightTabs() {
        return rightTabs;
    }

    private List<IComponent<?>> getAllTabs() {
        List<IComponent<?>> allTabs = new ArrayList<>();
        allTabs.addAll(this.getLeftTabs().getTabComponents());
        allTabs.addAll(this.getRightTabs().getTabComponents());
        return allTabs;
    }
}
