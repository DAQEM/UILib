package com.daqem.uilib.test.component;

import com.daqem.uilib.UILib;
import com.daqem.uilib.gui.component.AbstractComponent;
import com.daqem.uilib.gui.widget.*;
import com.daqem.uilib.test.TestScreen;
import com.daqem.uilib.test.component.sprite.TestSpriteComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;


public class TestWidgetsComponent extends AbstractComponent {

    public TestWidgetsComponent(int number) {
        super(0, 0, 200, 150);

        Component text = UILib.translatable("component.test.number", number);

        TestSpriteComponent spriteComponent = new TestSpriteComponent(-10, -10, 220, 170);

        ButtonWidget button = new ButtonWidget(0, 0, 200, 20, text, button1 -> {
            if (Minecraft.getInstance().screen instanceof TestScreen screen) {
                screen.onClose();
            }
        });

        EditBoxWidget editBox = new EditBoxWidget(Minecraft.getInstance().font, 0, 22, 200, 20, Component.literal("test"));
        editBox.setValue("This is a test!");
        editBox.setMaxLength(128);

        MultiLineEditBoxWidget multiLineEditBox = new MultiLineEditBoxWidget(Minecraft.getInstance().font, 0, 44, 200, 60, text, Component.literal("test"));
        multiLineEditBox.setValue("This is a test!\nWith multiple lines!\nLine 3\nLine 4\nLine 5\nLine 6\nLine 7\nLine 8\nLine 9\nLine 10");

        this.addComponent(spriteComponent);
        this.addWidget(button);
        this.addWidget(editBox);
        this.addWidget(multiLineEditBox);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}
