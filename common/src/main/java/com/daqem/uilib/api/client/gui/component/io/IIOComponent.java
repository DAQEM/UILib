package com.daqem.uilib.api.client.gui.component.io;

import com.daqem.uilib.api.client.gui.component.IComponent;

public interface IIOComponent<T extends IIOComponent<T>> extends IComponent<T> {

    String getStringValue();
}
