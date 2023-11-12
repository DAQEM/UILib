package com.daqem.uilib.client.gui.texture;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import com.daqem.uilib.client.UILibClient;
import net.minecraft.resources.ResourceLocation;

public class Textures {

    private static final ResourceLocation GUI_TEXTURES = UILibClient.getId("textures/gui.png");
    public static final NineSlicedTexture BUTTON = new NineSlicedTexture(GUI_TEXTURES, 96, 0, 32, 16, 4);
    public static final NineSlicedTexture TAB = new NineSlicedTexture(GUI_TEXTURES, 32, 0, 16, 16, 4);
    public static final NineSlicedTexture MINECRAFT_BUTTON = new NineSlicedTexture(new ResourceLocation("textures/gui/widgets.png"), 0, 66, 200, 20, 20, 4);
    public static final NineSlicedTexture MINECRAFT_BUTTON_HOVERED = new NineSlicedTexture(new ResourceLocation("textures/gui/widgets.png"), 0, 86, 200, 20, 20, 4);
    public static final NineSlicedTexture SCROLL_PANE = new NineSlicedTexture(GUI_TEXTURES, 128, 0, 32, 32, 1);
    public static final NineSlicedTexture SCROLL_BAR = new NineSlicedTexture(GUI_TEXTURES, 0, 0, 8, 255, 2);
    public static final NineSlicedTexture SCROLL_BAR_BACKGROUND = new NineSlicedTexture(GUI_TEXTURES, 160, 0, 32, 32, 1);
}
