package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.screen.test.components.TestBackgroundComponent;
import net.minecraft.client.gui.GuiGraphics;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    @Override
    public void onStartScreen() {
        this.setBackground(Backgrounds.getDefaultBackground(this));

        TestBackgroundComponent testBackgroundComponent = new TestBackgroundComponent();

        addComponents(testBackgroundComponent);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
    }
}
