package com.daqem.uilib.client.gui.texture.icon;

import com.daqem.uilib.client.UILibClient;
import net.minecraft.resources.ResourceLocation;

public class Icons {

    private static final ResourceLocation ICONS_LOCATION = UILibClient.getId("textures/icons.png");
    public static final IconTexture BOOK = new IconTexture(ICONS_LOCATION, 0, 0);
    public static final IconTexture CHECK_MARK = new IconTexture(ICONS_LOCATION, 0, 20);
    public static final IconTexture CROSS = new IconTexture(ICONS_LOCATION, 0, 40);
    public static final IconTexture INFO = new IconTexture(ICONS_LOCATION, 0, 60);
    public static final IconTexture CRAFTING_TABLE = new IconTexture(ICONS_LOCATION, 0, 80);
    public static final IconTexture ARROW_UP = new IconTexture(ICONS_LOCATION, 0, 100);
    public static final IconTexture EXP_ORB = new IconTexture(ICONS_LOCATION, 0, 120);
    public static final IconTexture BOSS_BARS = new IconTexture(ICONS_LOCATION, 0, 140);
    public static final IconTexture GEAR = new IconTexture(ICONS_LOCATION, 0, 160);
}
