package com.daqem.uilib.client.gui.background;

import com.daqem.uilib.api.client.gui.IScreen;
import com.daqem.uilib.client.gui.texture.Texture;
import net.minecraft.resources.ResourceLocation;

public class Backgrounds {

    public static GradientBackground getDefaultBackground(IScreen screen) {
        return new GradientBackground(screen.getWidth(), screen.getHeight(), 0xC0101010, 0xD0101010);
    }

    public static TextureBackground getDirtBackground(IScreen screen) {
        return new TextureBackground(screen.getWidth(), screen.getHeight(), new Texture(new ResourceLocation("textures/gui/options_background.png"), 0, 0, 32, 32));
    }
}
