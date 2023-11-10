package com.daqem.uilib.objects.texture;

import com.daqem.uilib.client.gui.texture.Texture;
import net.minecraft.resources.ResourceLocation;

public class TestTexture extends Texture {

    public TestTexture() {
        super(null, 0, 0, 0, 0);
    }

    public TestTexture(ResourceLocation textureLocation, int x, int y, int width, int height) {
        super(textureLocation, x, y, width, height);
    }
}
