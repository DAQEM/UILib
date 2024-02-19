package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.component.advancement.AdvancementsComponent;
import com.daqem.uilib.client.screen.test.components.TestAdvancementTree;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    @Override
    public void startScreen() {
        this.setPauseScreen(false);
        this.setBackground(Backgrounds.getDefaultBackground(this.getWidth(), this.getHeight()));

        AdvancementsComponent advancementsComponent = new AdvancementsComponent(List.of(new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree(), new TestAdvancementTree()));
        advancementsComponent.center();
        addComponent(advancementsComponent);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

    }
}
