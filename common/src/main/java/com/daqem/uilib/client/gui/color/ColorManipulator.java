package com.daqem.uilib.client.gui.color;

import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import org.jetbrains.annotations.Nullable;

public class ColorManipulator implements IColorManipulator {

    private float red = 1F;
    private float green = 1F;
    private float blue = 1F;
    private float opacity = 1F;

    @Override
    public float getRed() {
        return red;
    }

    @Override
    public float getGreen() {
        return green;
    }

    @Override
    public float getBlue() {
        return blue;
    }

    @Override
    public float getOpacity() {
        return opacity;
    }

    @Override
    public void exposure(float exposureAmount) {
        this.red = exposureAmount;
        this.green = exposureAmount;
        this.blue = exposureAmount;
    }

    @Override
    public void color(float red, float green, float blue, float opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    @Override
    public void reset() {
        this.red = 1F;
        this.green = 1F;
        this.blue = 1F;
        this.opacity = 1F;
    }

    @Override
    public @Nullable Object getClone() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
