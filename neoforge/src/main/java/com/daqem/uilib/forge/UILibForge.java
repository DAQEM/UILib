package com.daqem.uilib.forge;

import com.daqem.uilib.client.UILibClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@Mod(UILibClient.MOD_ID)
public class UILibForge {

    public UILibForge(IEventBus modEventBus, ModContainer modContainer) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            new Client(modEventBus, modContainer);
        }
    }

    private static class Client {

        private final IEventBus modEventBus;

        private Client(IEventBus modEventBus, ModContainer modContainer) {
            this.modEventBus = modEventBus;

            UILibClient.init();
            registerEvents();
        }

        private void registerEvents() {
            modEventBus.addListener(this::registerKeyBindings);
        }

        private void registerKeyBindings(RegisterKeyMappingsEvent event) {
            event.register(UILibClient.OPEN_TEST_MENU);
        }

    }
}
