package com.daqem.uilib.api.widget;

import java.util.List;

public interface IWidgetsParent {

    List<IWidget> getWidgets();

    void addWidget(IWidget widget);
    void addWidgets(List<? extends IWidget> widgets);

    void removeWidget(IWidget widget);
    void removeWidgets(List<? extends IWidget> widgets);

    void clearOnlyWidgets();
}
