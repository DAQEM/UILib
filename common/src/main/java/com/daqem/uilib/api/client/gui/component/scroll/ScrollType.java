package com.daqem.uilib.api.client.gui.component.scroll;

public enum ScrollType {
    SMOOTH,
    STEPPED;

    public boolean isSmooth() {
        return this == SMOOTH;
    }
}
