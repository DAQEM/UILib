package com.daqem.uilib.client.gui.color;

import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import org.jetbrains.annotations.Nullable;

public class ColorManipulator implements IColorManipulator {

    private float red;
    private float green;
    private float blue;
    private float opacity;

    public ColorManipulator() {
        this(1F, 1F, 1F, 1F);
    }

    public ColorManipulator(float rgba) {
        this(rgba, rgba, rgba, rgba);
    }

    public ColorManipulator(float red, float green, float blue) {
        this(red, green, blue, 1F);
    }

    public ColorManipulator(float red, float green, float blue, float opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

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
