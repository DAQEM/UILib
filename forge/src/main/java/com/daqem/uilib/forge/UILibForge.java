package com.daqem.uilib.forge;

import com.daqem.uilib.client.UILibClient;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UILibClient.MOD_ID)
public class UILibForge {
    public UILibForge() {
        EventBuses.registerModEventBus(UILibClient.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> Client::new);
    }

    private static class Client {

        private Client() {
            UILibClient.init();
            registerEvents();
        }

        private void registerEvents() {
            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
            eventBus.addListener(this::registerKeyBindings);
        }

        private void registerKeyBindings(RegisterKeyMappingsEvent event) {
            event.register(UILibClient.OPEN_TEST_MENU);
        }

    }
}
