package com.daqem.uilib;

public class UILib {
    public static final String MOD_ID = "uilib";
    
    public static void init() {
        System.out.println(ExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
