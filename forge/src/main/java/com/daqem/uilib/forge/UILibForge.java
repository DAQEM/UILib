package com.daqem.uilib.forge;

import dev.architectury.platform.forge.EventBuses;
import com.daqem.uilib.UILib;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UILib.MOD_ID)
public class UILibForge {
    public UILibForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(UILib.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        UILib.init();
    }
}
