package com.daqem.uilib.api;

import com.daqem.uilib.api.component.IComponentsParent;
import com.daqem.uilib.api.widget.IWidgetsParent;

public interface IParent extends IComponentsParent, IWidgetsParent {

    void clear();
}
