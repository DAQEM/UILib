package com.daqem.uilib;


import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class UILib {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "uilib";

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
