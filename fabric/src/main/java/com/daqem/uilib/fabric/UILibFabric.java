package com.daqem.uilib.fabric;

import com.daqem.uilib.UILib;
import net.fabricmc.api.ModInitializer;

public class UILibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        UILib.init();
    }
}
