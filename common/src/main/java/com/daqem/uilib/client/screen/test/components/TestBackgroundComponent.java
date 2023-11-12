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
import com.daqem.uilib.client.gui.text.TruncatedText;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

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
        this.addChild(scrollPane);

        NineSlicedBackground scrollBarBackground = Backgrounds.getScrollBarBackground(scrollBar.getWidth() + 2, scrollBar.getHeight() + 2);
        scrollBar.setBackground(scrollBarBackground);
        scrollBar.setScrollBar(new ScrollBar(Textures.SCROLL_BAR, 0, 0, scrollBar.getWidth()));
        scrollPane.setScrollBar(scrollBar);
        ScrollContent content = new ScrollContent(0, 0, 0, ScrollOrientation.VERTICAL);
        scrollPane.setContent(content);
        ButtonComponent component = new ButtonComponent(Textures.SCROLL_BAR_BACKGROUND, 0, 0, 116, 20);
        TruncatedText testinggggggggggggg = new TruncatedText(Minecraft.getInstance().font, Component.literal("Testingggggggggggggjhhsgghsbdfbshjfb"), 0, 0, 116, 20);
        testinggggggggggggg.setCenter(true, true);
        component.setText(testinggggggggggggg);
        content.addComponent(component);
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
        content.addComponent((ButtonComponent) component.getClone());
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
