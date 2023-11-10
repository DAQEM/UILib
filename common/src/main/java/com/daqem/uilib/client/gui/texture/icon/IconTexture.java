package com.daqem.uilib.client.gui.texture.icon;

import com.daqem.uilib.client.gui.texture.Texture;
import net.minecraft.resources.ResourceLocation;

public class IconTexture extends Texture {

    public IconTexture(ResourceLocation textureLocation, int x, int y) {
        super(textureLocation, x, y, 20, 20);
    }

    public IconTexture(ResourceLocation textureLocation, int x, int y, int width, int height) {
        super(textureLocation, x, y, width, height);
    }

    public IconTexture(ResourceLocation textureLocation, int x, int y, int width, int height, int fileSize) {
        super(textureLocation, x, y, width, height, fileSize);
    }

    public IconTexture(ResourceLocation textureLocation, int x, int y, int width, int height, int fileWidth, int fileHeight) {
        super(textureLocation, x, y, width, height, fileWidth, fileHeight);
    }
}
