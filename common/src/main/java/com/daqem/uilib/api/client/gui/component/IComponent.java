package com.daqem.uilib.api.client.gui.component;

import com.daqem.uilib.api.client.gui.ICenterable;
import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.color.IColorManipulatable;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.api.client.gui.texture.ITexture;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IComponent<T extends IComponent<T>> extends IRenderable<T>, IColorManipulatable, ICenterable {


    @Nullable IComponent<?> getParent();
    List<IComponent<?>> getChildren();
    ITexture getTexture();
    int getZ();
    IText<?> getText();
    float getScale();
    float getOpacity();
    float getRotation();
    boolean renderBeforeParent();
    boolean isFocused();
    void setFocused(boolean focused);


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

    void renderTooltipsBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);
    void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);
    void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);
}
