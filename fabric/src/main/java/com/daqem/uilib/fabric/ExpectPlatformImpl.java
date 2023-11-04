package com.daqem.uilib.fabric;

import com.daqem.uilib.ExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ExpectPlatformImpl {
    /**
     * This is our actual method to {@link ExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
