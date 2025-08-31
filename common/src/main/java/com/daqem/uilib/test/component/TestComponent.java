package com.daqem.uilib.test.component;

import com.daqem.uilib.UILib;
import com.daqem.uilib.gui.component.AbstractComponent;
import com.daqem.uilib.gui.component.text.ScrollingTextComponent;
import com.daqem.uilib.gui.component.text.TextAlign;
import com.daqem.uilib.gui.component.text.TextComponent;
import com.daqem.uilib.gui.component.text.TruncatedTextComponent;
import com.daqem.uilib.gui.widget.ButtonWidget;
import com.daqem.uilib.test.component.sprite.TestSpriteComponent;
import net.minecraft.client.gui.GuiGraphics;

public class TestComponent extends AbstractComponent {

    public TestComponent(int number) {
        super(0, 0, 200, 40);

        ButtonWidget buttonWidget = new ButtonWidget(0, 20, 200, 20, UILib.translatable("widget.test.button"));
        TestSpriteComponent spriteComponent = new TestSpriteComponent(-10, -10, 220, 60);
        TextComponent textComponent = new TextComponent(200, 0, UILib.translatable("component.test.number", number), 0xFF0000FF);
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent(0, 0, 30,UILib.translatable("component.test.number", number), 0xFF00FF00);
        ScrollingTextComponent scrollingTextComponent = new ScrollingTextComponent(100, 0, 30, UILib.translatable("component.test.number", number), 0xFFFF0000);

        spriteComponent.setRenderBeforeParent(true);
        textComponent.setTextAlign(TextAlign.RIGHT);
        scrollingTextComponent.setTextAlign(TextAlign.CENTER);

        this.addWidget(buttonWidget);
        this.addComponent(spriteComponent);
        this.addComponent(textComponent);
        this.addComponent(truncatedTextComponent);
        this.addComponent(scrollingTextComponent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}
