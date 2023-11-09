package com.daqem.uilib.client.gui.texture;

import com.daqem.uilib.client.UILibClient;
import net.minecraft.resources.ResourceLocation;

public class Textures {

    private static final ResourceLocation GUI_TEXTURES = UILibClient.getId("textures/gui.png");
    public static NineSlicedTexture BUTTON = new NineSlicedTexture(GUI_TEXTURES, 0, 0, 9, 9, 4);
    public static NineSlicedTexture TAB = new NineSlicedTexture(GUI_TEXTURES, 0, 9, 9, 9, 4);
    public static NineSlicedTexture MINECRAFT_BUTTON = new NineSlicedTexture(new ResourceLocation("textures/gui/widgets.png"), 0, 66, 200, 20, 20, 4);
    public static NineSlicedTexture MINECRAFT_BUTTON_HOVERED = new NineSlicedTexture(new ResourceLocation("textures/gui/widgets.png"), 0, 86, 200, 20, 20, 4);
}
