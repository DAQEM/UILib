package com.daqem.uilib.forge;

import com.daqem.uilib.client.UILibClient;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.fml.common.Mod;

@Mod(UILibClient.MOD_ID)
public class UILibForge {

    public UILibForge() {
        EnvExecutor.runInEnv(Env.CLIENT, () -> UILibClient::init);
    }
}
