package com.daqem.uilib.client.gui.texture;

import com.daqem.uilib.api.client.gui.texture.INineSlicedTexture;
import com.daqem.uilib.client.gui.texture.Texture;
import net.minecraft.resources.ResourceLocation;

public class NiceSlicedTexture extends Texture implements INineSlicedTexture {

    private final int leftSliceWidth;
    private final int topSliceHeight;
    private final int rightSliceWidth;
    private final int bottomSliceHeight;

    public NiceSlicedTexture(ResourceLocation textureLocation, int x, int y, int width, int height, int SliceSize) {
        this(textureLocation, x, y, width, height, SliceSize, SliceSize);
    }

    public NiceSlicedTexture(ResourceLocation textureLocation, int x, int y, int width, int height, int SliceWidth, int SliceHeight) {
        this(textureLocation, x, y, width, height, SliceWidth, SliceHeight, SliceWidth, SliceHeight);
    }

    public NiceSlicedTexture(ResourceLocation textureLocation, int x, int y, int width, int height, int leftSliceWidth, int topSliceHeight, int rightSliceWidth, int bottomSliceHeight) {
        super(textureLocation, x, y, width, height);
        this.leftSliceWidth = leftSliceWidth;
        this.topSliceHeight = topSliceHeight;
        this.rightSliceWidth = rightSliceWidth;
        this.bottomSliceHeight = bottomSliceHeight;
    }

    @Override
    public int getLeftSliceWidth() {
        return leftSliceWidth;
    }

    @Override
    public int getTopSliceHeight() {
        return topSliceHeight;
    }

    @Override
    public int getRightSliceWidth() {
        return rightSliceWidth;
    }

    @Override
    public int getBottomSliceHeight() {
        return bottomSliceHeight;
    }
}
