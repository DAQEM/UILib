package com.daqem.uilib.test.component;

import com.daqem.uilib.UILib;
import com.daqem.uilib.gui.widget.ButtonWidget;
import com.daqem.uilib.test.component.sprite.TestSpriteComponent;
import net.minecraft.network.chat.Component;

public class TestScrollContentComponent extends TestSpriteComponent {

    public TestScrollContentComponent(int x, int y, int width, int height) {
        super(x, y, width, height);

        ButtonWidget buttonWidget1 = new ButtonWidget(10, 10, 160, 20, Component.literal("Test1"), button -> {
            UILib.LOGGER.info("Test1");
        });
        ButtonWidget buttonWidget2 = new ButtonWidget(10, 40, 160, 20, Component.literal("Test2"), button -> {
            UILib.LOGGER.info("Test2");
        });

        this.addWidget(buttonWidget1);
        this.addWidget(buttonWidget2);
    }
}
