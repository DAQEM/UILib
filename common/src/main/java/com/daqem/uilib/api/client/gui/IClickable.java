package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.component.action.OnClickAction;

public interface IClickable<T extends IClickable<T>> {

    boolean isClicked(double mouseX, double mouseY);

    void preformOnClickAction();

    OnClickAction<T> getOnClickAction();

    void setOnClickAction(OnClickAction<T> onClickAction);
}
