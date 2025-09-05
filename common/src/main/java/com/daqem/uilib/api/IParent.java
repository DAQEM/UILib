package com.daqem.uilib.api;

import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.widget.IWidget;

import java.util.List;

public interface IParent {

    void addComponent(IComponent component);
    void addComponents(List<? extends IComponent> components);

    void removeComponent(IComponent component);
    void removeComponents(List<? extends IComponent> components);

    void clearComponents();

    void addWidget(IWidget widget);
    void addWidgets(List<? extends IWidget> widgets);

    void removeWidget(IWidget widget);
    void removeWidgets(List<? extends IWidget> widgets);

    void clearOnlyWidgets();

    void clear();
}
