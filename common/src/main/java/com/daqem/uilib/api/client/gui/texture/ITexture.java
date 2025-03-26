package com.daqem.uilib.api.client.gui.texture;

import net.minecraft.resources.ResourceLocation;

public interface ITexture {

    ResourceLocation getTextureLocation();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    int getFileWidth();
    int getFileHeight();

    void setTextureLocation(ResourceLocation textureLocation);
    void setX(int x);
    void setY(int y);
    void setWidth(int width);
    void setHeight(int height);
    void setFileWidth(int fileWidth);
    void setFileHeight(int fileHeight);
}
