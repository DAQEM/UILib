package com.daqem.uilib.client.gui.texture;

import com.daqem.uilib.client.UILibClient;
import net.minecraft.resources.ResourceLocation;

public class Textures {

    private static final ResourceLocation GUI_TEXTURES = UILibClient.getId("textures/gui.png");
    public static NiceSlicedTexture BUTTON = new NiceSlicedTexture(GUI_TEXTURES, 0, 0, 9, 9, 4);
    public static NiceSlicedTexture MINECRAFT_BUTTON = new NiceSlicedTexture(new ResourceLocation("textures/gui/widgets.png"), 0, 66, 200, 20, 20, 4);
}
