package com.daqem.uilib.test;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

public class TestMod {
    public static final String MOD_ID = "uilib_test";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final KeyMapping.Category UI_LIB_CATEGORY = new KeyMapping.Category(getId("category"));
    public static final KeyMapping OPEN_TEST_MENU = new KeyMapping("key.uilib_test.open_test_menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, UI_LIB_CATEGORY);

    public static void init() {

    }

    public static ResourceLocation getId(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static MutableComponent translatable(String str) {
        return Component.translatable(MOD_ID + "." + str);
    }

    public static MutableComponent translatable(String str, Object... objects) {
        return Component.translatable(MOD_ID + "." + str, objects);
    }

    public static MutableComponent literal(String str) {
        return Component.literal(str);
    }
}
