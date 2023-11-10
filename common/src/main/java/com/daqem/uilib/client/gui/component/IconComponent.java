package com.daqem.uilib.client.gui.component;

import com.daqem.uilib.client.gui.texture.icon.IconTexture;

public class IconComponent extends TextureComponent
{
    public IconComponent(IconTexture texture) {
        super(texture, 0, 0, 20, 20);
    }
    public IconComponent(IconTexture texture, int x, int y) {
        super(texture, x, y, 20, 20);
    }
    public IconComponent(IconTexture texture, int x, int y, int width, int height) {
        super(texture, x, y, width, height);
    }
}
