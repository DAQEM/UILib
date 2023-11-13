package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollBar;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarWrapper;
import com.daqem.uilib.client.gui.component.scroll.ScrollContent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPaneComponent;
import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;

import java.util.ArrayList;
import java.util.List;

public class TestBackgroundComponent extends TextureComponent {

    private final TestLeftTabs leftTabs = new TestLeftTabs();
    private final TestRightTabs rightTabs = new TestRightTabs();

    private final ScrollPaneComponent scrollPane;
    private final ScrollPaneComponent scrollPane2;

    public TestBackgroundComponent() {
        super(new Texture(UILibClient.getId("textures/jobs_screen.png"),
                        0, 0, 326, 166, 362),
                0, 0, 326, 166);

        {
            ScrollOrientation orientation = ScrollOrientation.VERTICAL;
            int scrollPaneX = 7;
            int scrollPaneY = 15;
            int scrollPaneWidth = 116;
            int scrollPaneHeight = 140;
            int scrollBarWidth = 12;
            int scrollBarXOffset = 4;
            int scrollBarYOffset = 1;

            NineSlicedTexture scrollPaneTexture = Textures.SCROLL_PANE;
            NineSlicedTexture scrollBarTexture = Textures.SCROLL_BAR;
            NineSlicedTexture scrollBarBackgroundTexture = Textures.SCROLL_BAR_BACKGROUND;

            ScrollBar scrollBar = new ScrollBar(scrollBarTexture, 0, 0, scrollBarWidth);
            ScrollBarWrapper scrollBarWrapper = new ScrollBarWrapper(scrollPaneWidth + scrollBarXOffset, scrollBarYOffset, scrollBarWidth, scrollPaneHeight - (scrollBarYOffset * 2), orientation, scrollBar);
            ScrollContent content = new ScrollContent(0, 0, 0, orientation);

            ButtonComponent component = new ButtonComponent(scrollBarBackgroundTexture, 0, 0, scrollPaneWidth, 20);
            content.addChild(component);
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());

            this.scrollPane = new ScrollPaneComponent(scrollPaneTexture, scrollPaneX, scrollPaneY, scrollPaneWidth, scrollPaneHeight, orientation, content, scrollBarWrapper);
        }
        {
            ScrollOrientation orientation = ScrollOrientation.HORIZONTAL;
            int scrollPaneX = 157;
            int scrollPaneY = 15;
            int scrollPaneWidth = 116;
            int scrollPaneHeight = 140;
            int scrollBarWidth = 12;
            int scrollBarXOffset = 4;
            int scrollBarYOffset = 1;

            NineSlicedTexture scrollPaneTexture = Textures.SCROLL_PANE;
            NineSlicedTexture scrollBarTexture = Textures.SCROLL_BAR;
            NineSlicedTexture scrollBarBackgroundTexture = Textures.SCROLL_BAR_BACKGROUND;

//            ScrollBar scrollBar = new ScrollBar(scrollBarTexture, 0, 0, scrollBarWidth);
//            ScrollBarWrapper scrollBarWrapper = new ScrollBarWrapper(scrollPaneWidth + scrollBarXOffset, scrollBarYOffset, scrollBarWidth, scrollPaneHeight - (scrollBarYOffset * 2), orientation, scrollBar);
            ScrollContent content = new ScrollContent(0, 0, 0, orientation);

            ButtonComponent component = new ButtonComponent(scrollBarBackgroundTexture, 0, 0, 6, scrollPaneHeight);
            content.addChild(component);
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());
            content.addChild((ButtonComponent) component.getClone());

            this.scrollPane2 = new ScrollPaneComponent(scrollPaneTexture, scrollPaneX, scrollPaneY, scrollPaneWidth, scrollPaneHeight, orientation, content, null);
        }
    }

    @Override
    public void startRenderable() {
        this.center();
        this.addChildren(getAllTabs());
        this.addChild(scrollPane);
        this.addChild(scrollPane2);
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
