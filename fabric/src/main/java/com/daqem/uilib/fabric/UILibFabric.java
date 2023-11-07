package com.daqem.uilib.fabric;

import com.daqem.uilib.client.UILibClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class UILibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UILibClient.init();
        registerKeyBindings();
    }

    private static void registerKeyBindings() {
        KeyBindingHelper.registerKeyBinding(UILibClient.OPEN_TEST_MENU);
    }

}
