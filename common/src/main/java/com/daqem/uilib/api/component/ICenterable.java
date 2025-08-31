package com.daqem.uilib.api.component;

public interface ICenterable {

    boolean isCenteredHorizontally();
    boolean isCenteredVertically();
    boolean isCentered();

    void centerHorizontally();
    void centerVertically();
    void center();

    void decenterHorizontally();
    void decenterVertically();
    void decenter();
}
