package com.daqem.uilib;


import com.daqem.uilib.event.EventKeyPressed;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

public class UILib {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "uilib";

    private static final KeyMapping.Category UI_LIB_CATEGORY = new KeyMapping.Category(getId("category"));
    public static final KeyMapping OPEN_TEST_MENU = new KeyMapping("key.uilib.open_test_menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, UI_LIB_CATEGORY);

    public static void init() {
        EventKeyPressed.registerEvent();
        KeyMappingRegistry.register(OPEN_TEST_MENU);
    }

    public static MutableComponent translatable(String resourceKey) {
        return Component.translatable(MOD_ID + "." + resourceKey);
    }

    public static MutableComponent translatable(String resourceKey, Object... args) {
        return Component.translatable(MOD_ID + "." + resourceKey, args);
    }

    public static ResourceLocation getId(String location) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, location);
    }
}
