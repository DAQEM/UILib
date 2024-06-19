package com.daqem.uilib.client.gui.texture;

import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class Texture implements ITexture {

    private ResourceLocation textureLocation;
    private int x;
    private int y;
    private int width;
    private int height;
    private int fileWidth;
    private int fileHeight;

    public Texture(ResourceLocation textureLocation, int x, int y, int width, int height) {
        this(textureLocation, x, y, width, height, 256, 256);
    }

    public Texture(ResourceLocation textureLocation, int x, int y, int width, int height, int fileSize) {
        this(textureLocation, x, y, width, height, fileSize, fileSize);
    }

    public Texture(ResourceLocation textureLocation, int x, int y, int width, int height, int fileWidth, int fileHeight) {
        this.textureLocation = textureLocation;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fileWidth = fileWidth;
        this.fileHeight = fileHeight;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getFileWidth() {
        return fileWidth;
    }

    @Override
    public int getFileHeight() {
        return fileHeight;
    }

    @Override
    public void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setFileWidth(int fileWidth) {
        this.fileWidth = fileWidth;
    }

    @Override
    public void setFileHeight(int fileHeight) {
        this.fileHeight = fileHeight;
    }

    @Override
    public @Nullable Object getClone() {
        try {
            ITexture clone = (ITexture) this.clone();
            ResourceLocation currentTextureLocation = clone.getTextureLocation();
            clone.setTextureLocation(ResourceLocation.fromNamespaceAndPath(currentTextureLocation.getNamespace(), currentTextureLocation.getPath()));
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
