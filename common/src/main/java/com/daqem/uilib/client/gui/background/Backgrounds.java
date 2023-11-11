package com.daqem.uilib.client.gui.background;

import com.daqem.uilib.api.client.gui.IScreen;
import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.resources.ResourceLocation;

public class Backgrounds {

    public static GradientBackground getDefaultBackground(int width, int height) {
        return new GradientBackground(width, height, 0xC0101010, 0xD0101010);
    }

    public static TextureBackground getDirtBackground(int width, int height) {
        return new TextureBackground(width, height, new Texture(new ResourceLocation("textures/gui/options_background.png"), 0, 0, 32, 32));
    }

    public static NineSlicedBackground getScrollBarBackground(int width, int height) {
        return new NineSlicedBackground(width, height, Textures.SCROLL_BAR_BACKGROUND);
    }
}
