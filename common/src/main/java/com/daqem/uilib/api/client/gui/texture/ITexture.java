package com.daqem.uilib.api.client.gui.texture;

import com.daqem.uilib.api.client.gui.ICloneable;
import net.minecraft.resources.ResourceLocation;

public interface ITexture extends ICloneable {

    ResourceLocation getTextureLocation();
    int getX();
    int getY();
    int getWidth();
    int getHeight();

    void setTextureLocation(ResourceLocation textureLocation);
    void setX(int x);
    void setY(int y);
    void setWidth(int width);
    void setHeight(int height);
}
