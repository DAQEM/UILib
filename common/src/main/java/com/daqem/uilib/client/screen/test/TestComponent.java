package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.io.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Difficulty;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestComponent extends AbstractComponent<TestComponent> {

    public TestComponent(int x, int y, int width, int height) {
        super(null, x, y, width, height);
    }

    @Override
    public void startRenderable() {

        SliderComponent<Difficulty> sliderComponent = new SliderComponent<>(10, 153, 100, 20,
                Arrays.stream(Difficulty.values())
                        .map(difficulty -> new IOComponentEntry<>(difficulty.name(), Component.literal(difficulty.name()), difficulty))
                        .collect(Collectors.toList()),
                Difficulty.HARD,
                Component.literal("Difficulty"));
        this.addChild(sliderComponent);

        TextBoxComponent textBoxComponent = new TestStringTextBoxComponent(10, 10, 100, 20, "Test Text Box");
        this.addChild(textBoxComponent);

        MultiLineTextBoxComponent multiLineTextBoxComponent = new TestIntegerMultiLineTextBoxComponent(10, 31, 100, 100);
        this.addChild(multiLineTextBoxComponent);

        CycleButtonComponent<Difficulty> cycleButtonComponent = new CycleButtonComponent<>(10, 132, 100, 20,
                Arrays.stream(Difficulty.values())
                        .map(difficulty -> new IOComponentEntry<>(difficulty.name(), Component.literal(difficulty.name()), difficulty))
                        .collect(Collectors.toList()),
                Difficulty.NORMAL,
                Component.literal("Difficulty"));
        this.addChild(cycleButtonComponent);

        ButtonComponent buttonComponent = new ButtonComponent(10, 174, 100, 20, Component.literal("Test Button"), (clickedObject, screen, mouseX, mouseY, button) -> {
            Minecraft.getInstance().screen.onClose();
            return true;
        });
        this.addChild(buttonComponent);

        super.startRenderable();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {

    }
}
