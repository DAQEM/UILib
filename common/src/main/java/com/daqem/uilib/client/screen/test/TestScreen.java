package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.selection.SelectionListComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    @Override
    public void startScreen() {
        this.setPauseScreen(false);
        this.setBackground(Backgrounds.getDefaultBackground(this.getWidth(), this.getHeight()));

        SelectionListComponent selectionListComponent = new SelectionListComponent(
                0, 0, 166, 200,
                this.font, Component.literal("Test"),
                List.of(
                        new SelectionItem(26, Component.literal("Test 1"), Component.literal("Test 1 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 1"));
                        }),
                        new SelectionItem(26, Component.literal("Test 2"), Component.literal("Test 2 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 2"));
                        }),
                        new SelectionItem(26, Component.literal("Test 3"), Component.literal("Test 3 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 3"));
                        }),
                        new SelectionItem(26, Component.literal("Test 4"), Component.literal("Test 4 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 4"));
                        }),
                        new SelectionItem(26, Component.literal("Test 5"), Component.literal("Test 5 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 5"));
                        }),
                        new SelectionItem(26, Component.literal("Test 6"), Component.literal("Test 6 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 6"));
                        }),
                        new SelectionItem(26, Component.literal("Test 7"), Component.literal("Test 7 Description"), (clickedObject, screen, mouseX, mouseY, button) -> {
                            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Test 7"));
                        })
                ));
        selectionListComponent.center();
        this.addComponent(selectionListComponent);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
    }
}
