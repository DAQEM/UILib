package com.daqem.uilib.forge;

import com.daqem.uilib.UILib;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.fml.common.Mod;

@Mod(UILib.MOD_ID)
public class UILibForge {

    public UILibForge() {
        EnvExecutor.runInEnv(Env.CLIENT, () -> UILib::init);
    }
}
