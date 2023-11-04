package com.daqem.uilib.client;


import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class UILibClient {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "uilib";

    public static void init() {
        LOGGER.info("Hello from UI Lib client!");
    }
}
