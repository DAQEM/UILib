package com.daqem.uilib.api.client.gui.component;

import com.daqem.uilib.api.client.gui.ICenterable;
import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.color.IColorManipulatable;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IComponent<T extends IComponent<T>> extends IRenderable<T>, IColorManipulatable, ICenterable {


    @Nullable IComponent<?> getParent();
    List<IComponent<?>> getChildren();
    ITexture getTexture();
    int getTotalX();
    int getTotalY();
    int getZ();
    IText<?> getText();
    float getScale();
    float getOpacity();
    float getRotation();
    boolean renderBeforeParent();


    void setParent(@Nullable IComponent<?> parent, boolean addAsChild);
    void setChildren(List<IComponent<?>> children);
    void addChild(IComponent<?> child);
    void addChildren(IComponent<?>... children);
    void addChildren(List<IComponent<?>> children);
    void removeChild(IComponent<?> child);
    void setTexture(ITexture texture);
    void setZ(int z);
    void setText(IText<?> text);
    void setScale(float scale);
    void setOpacity(float opacity);
    void setRotation(float rotation);
    void setRenderBeforeParent(boolean renderBeforeParent);
}
