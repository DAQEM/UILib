package com.daqem.uilib.api.client.gui;

public interface ICenterable {

    boolean isCenteredHorizontally();
    boolean isCenteredVertically();
    boolean isCentered();

    void centerHorizontally();
    void centerVertically();
    void center();
}
