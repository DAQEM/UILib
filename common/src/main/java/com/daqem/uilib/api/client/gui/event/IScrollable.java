package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnScrollEvent;

public interface IScrollable<T extends IScrollable<T>> {

    boolean isScrolled(double mouseX, double mouseY, double delta);

    void preformOnScrollEvent(double mouseX, double mouseY, double delta);

    OnScrollEvent<T> getOnScrollEvent();

    void setOnScrollEvent(OnScrollEvent<T> onScrollEvent);
}
