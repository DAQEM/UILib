package com.daqem.uilib.fabric;

import com.daqem.uilib.client.UILibClient;
import net.fabricmc.api.ClientModInitializer;

public class UILibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UILibClient.init();
    }
}
