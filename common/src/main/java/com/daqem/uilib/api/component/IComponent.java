package com.daqem.uilib.api.component;

import com.daqem.uilib.api.IParent;
import com.daqem.uilib.api.IRenderable;
import com.daqem.uilib.api.widget.IWidget;

import java.util.List;
import java.util.Map;

public interface IComponent extends IRenderable, ICenterable, IParent {
    
    void setWidth(int width);
    void setHeight(int height);
    int getParentX();
    int getParentY();
    int getTotalX();
    int getTotalY();

    List<IWidget> getAllWidgets();
    void getAllWidgetsMap(Map<IWidget, IComponent> map);

    boolean isRenderBeforeParent();
    void setRenderBeforeParent(boolean renderBeforeParent);
    boolean isRenderDebugBorder();
    void setRenderDebugBorder(boolean renderDebugBorder);

    void positionUpdated();
    void updateParentPosition(int parentX, int parentY, int parentWidth, int parentHeight);
}
