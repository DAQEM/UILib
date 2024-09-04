package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.io.*;
import com.daqem.uilib.client.gui.component.scroll.ScrollBarComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollContentComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPanelComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollWheelComponent;
import com.daqem.uilib.client.gui.component.selection.SelectionListComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Difficulty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    @Override
    public void startScreen() {
        this.setPauseScreen(false);
        this.setBackground(Backgrounds.getDefaultBackground(this.getWidth(), this.getHeight()));

//        SelectionListComponent selectionListComponent = new SelectionListComponent(
//                0, 0, 166, 200,
//                this.font, Component.literal("Test"),
//                List.of(
//                        new SelectionItem(26, Component.literal("Test 1"), Component.literal("Test 1 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 1"));
//                            return true;
//                        }),
//                        new SelectionItem(26, Component.literal("Test 2"), Component.literal("Test 2 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 2"));
//                            return true;
//                        }),
//                        new SelectionItem(26, Component.literal("Test 3"), Component.literal("Test 3 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 3"));
//                            return true;
//                        }),
//                        new SelectionItem(26, Component.literal("Test 4"), Component.literal("Test 4 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 4"));
//                            return true;
//                        }),
//                        new SelectionItem(26, Component.literal("Test 5"), Component.literal("Test 5 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 5"));
//                            return true;
//                        }),
//                        new SelectionItem(26, Component.literal("Test 6"), Component.literal("Test 6 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 6"));
//                            return true;
//                        }),
//                        new SelectionItem(26, Component.literal("Test 7"), Component.literal("Test 7 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
//                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 7"));
//                            return true;
//                        })
//                ));
//        selectionListComponent.center();
//        this.addComponent(selectionListComponent);

        ArrayList<IComponent<?>> configCategories = new ArrayList<>();

        IntStream.range(0, 30).forEach(modConfigs -> {
            ScrollContentComponent e = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
            configCategories.add(e);
            ScrollContentComponent e1 = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
            e.addChild(e1);
            ScrollContentComponent e2 = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
            e1.addChild(e2);
            ScrollContentComponent e3 = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
            e2.addChild(e3);
            ScrollContentComponent e4 = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
            e3.addChild(e4);
            e4.addChild(new TestComponent(0, 0, 300, 200));
            e4.addChild(new TestComponent(0, 0, 300, 200));
            e4.addChild(new TestComponent(0, 0, 300, 200));
            e4.addChild(new TestComponent(0, 0, 300, 200));
            e4.addChild(new TestComponent(0, 0, 300, 200));
        });

        ScrollContentComponent content = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
        ScrollWheelComponent scrollWheel = new ScrollWheelComponent(Textures.SCROLL_WHEEL, 0, 0, 6);
        ScrollBarComponent scrollBar = new ScrollBarComponent(305, 0, 6, getHeight() - 34 - 32, ScrollOrientation.VERTICAL, scrollWheel);
        scrollBar.setBackground(null);
        configCategories.forEach(content::addChild);
        ScrollPanelComponent scrollPanelComponent = new ScrollPanelComponent(Textures.SCROLL_BAR_BACKGROUND, 0, 34, 300, getHeight() - 34 - 32, ScrollOrientation.VERTICAL, content, scrollBar);
        scrollPanelComponent.center();
        this.addComponent(scrollPanelComponent);

//        TestComponent testComponent = new TestComponent(100, 50, 100, 100);
//        this.addComponent(testComponent);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
    }
}
