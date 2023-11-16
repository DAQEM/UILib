package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollWheelComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollContentComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPanelComponent;
import com.daqem.uilib.client.gui.text.Text;
import com.daqem.uilib.client.gui.texture.NineSlicedTexture;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class TestBackgroundComponent extends TextureComponent {

    private final TestLeftTabs leftTabs = new TestLeftTabs();
    private final TestRightTabs rightTabs = new TestRightTabs();

    private final ScrollPanelComponent scrollPane;
    private final ScrollPanelComponent scrollPane2;

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

            ScrollWheelComponent scrollWheelComponent = new ScrollWheelComponent(scrollBarTexture, 0, 0, scrollBarWidth);
            ScrollBarComponent scrollBarComponent = new ScrollBarComponent(scrollPaneWidth + scrollBarXOffset, scrollBarYOffset, scrollBarWidth, scrollPaneHeight - (scrollBarYOffset * 2), orientation, scrollWheelComponent);
            ScrollContentComponent content = new ScrollContentComponent(0, 0, 10, orientation);

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
            ButtonComponent x = (ButtonComponent) component.getClone();
            x.setText(new Text(Minecraft.getInstance().font, Component.literal("Test"), 0, 0));
            content.addChild(x);

            this.scrollPane = new ScrollPanelComponent(scrollPaneTexture, scrollPaneX, scrollPaneY, scrollPaneWidth, scrollPaneHeight, orientation, content, scrollBarComponent);
        }
        {
            ScrollOrientation orientation = ScrollOrientation.HORIZONTAL;
            int scrollPaneX = 158;
            int scrollPaneY = 15;
            int scrollPaneWidth = 160;
            int scrollPaneHeight = 123;
            int scrollBarThickness = 12;
            int scrollBarXOffset = 1;
            int scrollBarYOffset = 4;

            NineSlicedTexture scrollPaneTexture = Textures.SCROLL_PANE;
            NineSlicedTexture scrollBarTexture = Textures.SCROLL_BAR;
            NineSlicedTexture scrollBarBackgroundTexture = Textures.SCROLL_BAR_BACKGROUND;

            ScrollWheelComponent scrollWheelComponent = new ScrollWheelComponent(scrollBarTexture, 0, 0, scrollBarThickness);
            ScrollBarComponent scrollBarComponent = new ScrollBarComponent(scrollBarXOffset, scrollPaneHeight + scrollBarYOffset, scrollPaneWidth - (scrollBarXOffset * 2), scrollBarThickness, orientation, scrollWheelComponent);
            ScrollContentComponent content = new ScrollContentComponent(0, 0, 10, orientation);

            ButtonComponent component = new ButtonComponent(scrollBarBackgroundTexture, 0, 0, 20, scrollPaneHeight);
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
            ButtonComponent x = (ButtonComponent) component.getClone();
            x.setText(new Text(Minecraft.getInstance().font, Component.literal("Test"), 0, 0));
            content.addChild(x);

            this.scrollPane2 = new ScrollPanelComponent(scrollPaneTexture, scrollPaneX, scrollPaneY, scrollPaneWidth, scrollPaneHeight, orientation, content, scrollBarComponent);
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
