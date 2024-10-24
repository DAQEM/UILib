package com.daqem.uilib.client.gui.background;

import com.daqem.uilib.client.gui.component.scroll.ScrollBarComponent;
import com.daqem.uilib.client.gui.texture.Texture;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.resources.ResourceLocation;

public class Backgrounds {

    public static GradientBackground getDefaultBackground(int width, int height) {
        return new GradientBackground(width, height, 0xC0101010, 0xD0101010);
    }

    public static TextureBackground getDirtBackground(int width, int height) {
        return new TextureBackground(width, height, new Texture(ResourceLocation.parse("textures/gui/options_background.png"), 0, 0, 32, 32));
    }

    public static NineSlicedBackground getSolidScrollBarBackground(ScrollBarComponent scrollBarComponent) {
        return new NineSlicedBackground(scrollBarComponent.getWidth() + 2, scrollBarComponent.getHeight() + 2, Textures.SCROLL_BAR_BACKGROUND);
    }
}
