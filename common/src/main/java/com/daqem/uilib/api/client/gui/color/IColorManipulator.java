package com.daqem.uilib.api.client.gui.color;

public interface IColorManipulator {

    float getRed();
    float getGreen();
    float getBlue();
    float getOpacity();

    void exposure(float exposureAmount);
    void color(float red, float green, float blue, float opacity);
    void reset();
}
