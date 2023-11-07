package com.daqem.uilib.api.client.gui.component;

import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.color.IColorManipulatable;
import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import com.daqem.uilib.api.client.gui.component.action.OnClickAction;
import com.daqem.uilib.api.client.gui.component.action.OnHoverAction;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import org.jetbrains.annotations.Nullable;

public interface IComponent<T extends IComponent<T>> extends IRenderable<T>, IColorManipulatable {

    ITexture getTexture();
    int getZ();
    IText<?> getText();
    float getScale();
    float getOpacity();
    float getRotation();

    void setTexture(ITexture texture);
    void setZ(int z);
    void setText(IText<?> text);
    void setScale(float scale);
    void setOpacity(float opacity);
    void setRotation(float rotation);
}
