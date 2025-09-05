package com.daqem.uilib.test.component;

import com.daqem.uilib.UILib;
import com.daqem.uilib.gui.component.AbstractComponent;
import com.daqem.uilib.gui.component.text.ScrollingTextComponent;
import com.daqem.uilib.gui.component.text.TextAlign;
import com.daqem.uilib.gui.component.text.TextComponent;
import com.daqem.uilib.gui.component.text.TruncatedTextComponent;
import com.daqem.uilib.gui.component.text.multiline.MultiLineTextComponent;
import com.daqem.uilib.gui.component.text.multiline.TruncatedMultiLineTextComponent;
import com.daqem.uilib.gui.widget.ButtonWidget;
import com.daqem.uilib.test.component.sprite.TestSpriteComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TestTextsComponent extends AbstractComponent {

    public TestTextsComponent(int number) {
        super(0, 0, 200, 224);

        Component text = UILib.translatable("component.test.number", number);

        TestSpriteComponent spriteComponent = new TestSpriteComponent(-10, -10, 220, 244);
        TextComponent leftTextComponent = new TextComponent(0, 0, text, 0xFF0000FF);
        TextComponent centerTextComponent = new TextComponent(100, 0, text, 0xFF0000FF);
        TextComponent rightTextComponent = new TextComponent(200, 0, text, 0xFF0000FF);

        TruncatedTextComponent leftTruncatedTextComponent = new TruncatedTextComponent(0, 10, 35, text, 0xFF00FF00);
        TruncatedTextComponent centerTruncatedTextComponent = new TruncatedTextComponent(100, 10, 35, text, 0xFF00FF00);
        TruncatedTextComponent rightTruncatedTextComponent = new TruncatedTextComponent(200, 10, 35, text, 0xFF00FF00);

        ScrollingTextComponent leftScrollingTextComponent = new ScrollingTextComponent(0, 20, 35, text, 0xFFFF0000);
        ScrollingTextComponent centerScrollingTextComponent = new ScrollingTextComponent(100, 20, 35, text, 0xFFFF0000);
        ScrollingTextComponent rightScrollingTextComponent = new ScrollingTextComponent(200, 20, 35, text, 0xFFFF0000);

        TruncatedMultiLineTextComponent leftTruncatedMultiLineTextComponent = new TruncatedMultiLineTextComponent(0, 30, 200, 2, UILib.translatable("component.test.long_text"), 0xFFFFFF00);
        TruncatedMultiLineTextComponent centerTruncatedMultiLineTextComponent = new TruncatedMultiLineTextComponent(100, 50 - 1, 200, 2, UILib.translatable("component.test.long_text"), 0xFFFFFF00);
        TruncatedMultiLineTextComponent rightTruncatedMultiLineTextComponent = new TruncatedMultiLineTextComponent(200, 70 - 2, 200, 2, UILib.translatable("component.test.long_text"), 0xFFFFFF00);

        MultiLineTextComponent leftMultiLineTextComponent = new MultiLineTextComponent(0, 90 - 3, 200, UILib.translatable("component.test.long_text"), 0xFFFFFF00);
        MultiLineTextComponent centerMultiLineTextComponent = new MultiLineTextComponent(100, 140 - 7, 200, UILib.translatable("component.test.long_text"), 0xFFFFFF00);
        MultiLineTextComponent rightMultiLineTextComponent = new MultiLineTextComponent(200, 190 - 11, 200, UILib.translatable("component.test.long_text"), 0xFFFFFF00);

        leftTextComponent.setTextAlign(TextAlign.LEFT);
        centerTextComponent.setTextAlign(TextAlign.CENTER);
        rightTextComponent.setTextAlign(TextAlign.RIGHT);
        leftTextComponent.setRenderDebugBorder(true);
        centerTextComponent.setRenderDebugBorder(true);
        rightTextComponent.setRenderDebugBorder(true);

        leftTruncatedTextComponent.setTextAlign(TextAlign.LEFT);
        centerTruncatedTextComponent.setTextAlign(TextAlign.CENTER);
        rightTruncatedTextComponent.setTextAlign(TextAlign.RIGHT);
        leftTruncatedTextComponent.setRenderDebugBorder(true);
        centerTruncatedTextComponent.setRenderDebugBorder(true);
        rightTruncatedTextComponent.setRenderDebugBorder(true);

        leftScrollingTextComponent.setTextAlign(TextAlign.LEFT);
        centerScrollingTextComponent.setTextAlign(TextAlign.CENTER);
        rightScrollingTextComponent.setTextAlign(TextAlign.RIGHT);
        leftScrollingTextComponent.setRenderDebugBorder(true);
        centerScrollingTextComponent.setRenderDebugBorder(true);
        rightScrollingTextComponent.setRenderDebugBorder(true);

        leftTruncatedMultiLineTextComponent.setTextAlign(TextAlign.LEFT);
        centerTruncatedMultiLineTextComponent.setTextAlign(TextAlign.CENTER);
        rightTruncatedMultiLineTextComponent.setTextAlign(TextAlign.RIGHT);
        leftTruncatedMultiLineTextComponent.setRenderDebugBorder(true);
        centerTruncatedMultiLineTextComponent.setRenderDebugBorder(true);
        rightTruncatedMultiLineTextComponent.setRenderDebugBorder(true);

        leftMultiLineTextComponent.setTextAlign(TextAlign.LEFT);
        centerMultiLineTextComponent.setTextAlign(TextAlign.CENTER);
        rightMultiLineTextComponent.setTextAlign(TextAlign.RIGHT);
        leftMultiLineTextComponent.setRenderDebugBorder(true);
        centerMultiLineTextComponent.setRenderDebugBorder(true);
        rightMultiLineTextComponent.setRenderDebugBorder(true);

        this.addComponent(spriteComponent);

        this.addComponent(leftTextComponent);
        this.addComponent(centerTextComponent);
        this.addComponent(rightTextComponent);

        this.addComponent(leftTruncatedTextComponent);
        this.addComponent(centerTruncatedTextComponent);
        this.addComponent(rightTruncatedTextComponent);

        this.addComponent(leftScrollingTextComponent);
        this.addComponent(centerScrollingTextComponent);
        this.addComponent(rightScrollingTextComponent);

        this.addComponent(leftTruncatedMultiLineTextComponent);
        this.addComponent(centerTruncatedMultiLineTextComponent);
        this.addComponent(rightTruncatedMultiLineTextComponent);

        this.addComponent(leftMultiLineTextComponent);
        this.addComponent(centerMultiLineTextComponent);
        this.addComponent(rightMultiLineTextComponent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}
