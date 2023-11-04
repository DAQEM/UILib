package com.daqem.uilib.forge;

import com.daqem.uilib.client.UILibClient;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UILibClient.MOD_ID)
public class UILibForge {
    public UILibForge() {
        EventBuses.registerModEventBus(UILibClient.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> UILibClient::init);
    }
}
