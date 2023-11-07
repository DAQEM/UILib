package com.daqem.uilib.client.screen;

import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.background.TextureBackground;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.text.TruncatedText;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    @Override
    public void onStartScreen() {
        this.setBackground(Backgrounds.getDefaultBackground(this));

        int backgroundWidth = 326;
        int backgroundHeight = 166;
        Texture texture = new Texture(UILibClient.getId("textures/jobs_screen.png"), 0, 0, backgroundWidth, backgroundHeight, 362);
        TextureComponent background = new TextureComponent(texture, (getWidth() - backgroundWidth) / 2, (getHeight() - backgroundHeight) / 2, backgroundWidth, backgroundHeight);

        addComponents(background);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
    }
}
