package com.daqem.uilib.api.client.gui.text;

import com.daqem.uilib.api.client.gui.IRenderable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public interface IText<T extends IText<T>> extends IRenderable<T> {

    Font getFont();
    Component getText();
    int getTextColor();
    boolean isShadow();
    boolean isBold();
    boolean isItalic();
    boolean isUnderlined();
    boolean isStrikethrough();
    boolean isObfuscated();
    boolean isHorizontalCenter();
    boolean isVerticalCenter();

    void setFont(Font font);
    void setText(Component text);
    void setTextColor(int color);
    void setTextColor(ChatFormatting chatFormatting);
    void setShadow(boolean shadow);
    void setBold(boolean bold);
    void setItalic(boolean italic);
    void setUnderlined(boolean underlined);
    void setStrikethrough(boolean strikethrough);
    void setObfuscated(boolean obfuscated);
    void setHorizontalCenter(boolean horizontalCenter);
    void setVerticalCenter(boolean verticalCenter);
    void setCenter(boolean horizontalCenter, boolean verticalCenter);
}
