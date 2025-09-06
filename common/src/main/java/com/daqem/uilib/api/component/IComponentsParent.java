package com.daqem.uilib.api.component;

import java.util.List;

public interface IComponentsParent {

    List<IComponent> getComponents();

    void addComponent(IComponent component);
    void addComponents(List<? extends IComponent> components);

    void removeComponent(IComponent component);
    void removeComponents(List<? extends IComponent> components);

    void clearComponents();
}
