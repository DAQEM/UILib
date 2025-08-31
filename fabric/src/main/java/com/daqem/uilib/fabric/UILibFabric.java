package com.daqem.uilib.fabric;

import com.daqem.uilib.UILib;
import net.fabricmc.api.ClientModInitializer;

public class UILibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UILib.init();
    }
}
