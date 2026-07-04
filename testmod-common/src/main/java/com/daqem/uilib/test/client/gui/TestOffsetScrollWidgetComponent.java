package com.daqem.uilib.test.client.gui;

import com.daqem.uilib.gui.component.AbstractComponent;
import com.daqem.uilib.gui.widget.ScrollContainerWidget;
import com.daqem.uilib.test.client.gui.component.sprite.TestSpriteComponent;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class TestOffsetScrollWidgetComponent extends AbstractComponent {

    public TestOffsetScrollWidgetComponent(int number) {
        super(0, 0, 200, 200);

        TestSpriteComponent spriteComponent = new TestSpriteComponent(-10, -10, 220, 220);

        // Make the container smaller than the background
        ScrollContainerWidget scrollContainerWidget = new ScrollContainerWidget(160, 160, 10);

        // THIS IS THE CRITICAL TEST: A non-zero local offset!
        scrollContainerWidget.setPosition(20, 20);

        TestScrollContentComponent scrollContentWidget1 = new TestScrollContentComponent(0, 0, 150, 100);
        TestScrollContentComponent scrollContentWidget2 = new TestScrollContentComponent(0, 0, 150, 100);
        TestScrollContentComponent scrollContentWidget3 = new TestScrollContentComponent(0, 0, 150, 100);

        scrollContainerWidget.addComponent(scrollContentWidget1);
        scrollContainerWidget.addComponent(scrollContentWidget2);
        scrollContainerWidget.addComponent(scrollContentWidget3);

        this.addComponent(spriteComponent);
        this.addWidget(scrollContainerWidget);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}