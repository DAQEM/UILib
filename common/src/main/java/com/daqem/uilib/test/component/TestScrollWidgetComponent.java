package com.daqem.uilib.test.component;

import com.daqem.uilib.gui.component.AbstractComponent;
import com.daqem.uilib.gui.widget.ScrollContainerWidget;
import com.daqem.uilib.test.component.sprite.TestSpriteComponent;
import net.minecraft.client.gui.GuiGraphics;

public class TestScrollWidgetComponent extends AbstractComponent {

    public TestScrollWidgetComponent(int number) {
        super(0, 0, 200, 200);

        TestSpriteComponent spriteComponent = new TestSpriteComponent(-10, -10, 220, 220);

        ScrollContainerWidget scrollContainerWidget = new ScrollContainerWidget(200, 200, 10);

        TestScrollContentComponent scrollContentWidget1 = new TestScrollContentComponent(0, 0, 190, 100);
        TestScrollContentComponent scrollContentWidget2 = new TestScrollContentComponent(0, 0, 190, 100);
        TestScrollContentComponent scrollContentWidget3 = new TestScrollContentComponent(0, 0, 190, 100);
        TestScrollContentComponent scrollContentWidget4 = new TestScrollContentComponent(0, 0, 190, 100);
        TestScrollContentComponent scrollContentWidget5 = new TestScrollContentComponent(0, 0, 190, 100);

        scrollContainerWidget.addComponent(scrollContentWidget1);
        scrollContainerWidget.addComponent(scrollContentWidget2);
        scrollContainerWidget.addComponent(scrollContentWidget3);
        scrollContainerWidget.addComponent(scrollContentWidget4);
        scrollContainerWidget.addComponent(scrollContentWidget5);

        this.addComponent(spriteComponent);
        this.addWidget(scrollContainerWidget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}
