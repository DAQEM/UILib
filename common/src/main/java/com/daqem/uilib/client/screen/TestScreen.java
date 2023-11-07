package com.daqem.uilib.client.screen;

import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.AbstractScreen;
import com.daqem.uilib.client.gui.background.Backgrounds;
import com.daqem.uilib.client.gui.background.TextureBackground;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.text.TruncatedText;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;

public class TestScreen extends AbstractScreen {

    public TestScreen() {
        super(UILibClient.translatable("screen.test"));
    }

    @Override
    public void onStartScreen() {

        TextureBackground dirtBackground = Backgrounds.getDirtBackground(this);
        dirtBackground.getColorManipulator().exposure(0.25F);
        this.setBackground(dirtBackground);
        TruncatedText buttonText = new TruncatedText(this.font, UILibClient.translatable("button.test.test.test"), 5, 0, 110, 20);
        buttonText.setTextColor(0x000000);
        buttonText.setCenter(true, true);
        ButtonComponent button = new ButtonComponent(Textures.BUTTON, 100, 100, 120, 20, buttonText);

        TruncatedText buttonText2 = new TruncatedText(this.font, UILibClient.translatable("button.test.test.test"), 5, 0, 110, 20);
        buttonText2.setCenter(true, true);
        ButtonComponent button2 = new ButtonComponent(Textures.MINECRAFT_BUTTON, 100, 150, 120, 20, buttonText2);

        TruncatedText closeText = new TruncatedText(this.font, UILibClient.translatable("button.test.close"), 5, 0, 110, 20);
        closeText.setCenter(true, true);
        ButtonComponent closeButton = new ButtonComponent(Textures.MINECRAFT_BUTTON, 100, 200, 120, 20, closeText,
                btn -> onClose(),
                btn -> {
                    IText<?> text = btn.getText();
                    if (text != null) {
                        text.setTextColor(0xFF0000);
                        text.setShadow(true);
                    }
                    btn.setTexture(Textures.MINECRAFT_BUTTON_HOVERED);
                    btn.setRotation(90);
                });
        addComponents(closeButton, button2, button);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
    }
}
