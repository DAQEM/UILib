package com.daqem.uilib.api.client.gui.event;

import com.daqem.uilib.api.client.gui.component.event.OnClickEvent;

public interface IClickable<T extends IClickable<T>> {

    boolean isClicked(double mouseX, double mouseY, int button);

    boolean preformOnClickEvent(double mouseX, double mouseY, int button);

    OnClickEvent<T> getOnClickEvent();

    void setOnClickEvent(OnClickEvent<T> onClickEvent);
}
