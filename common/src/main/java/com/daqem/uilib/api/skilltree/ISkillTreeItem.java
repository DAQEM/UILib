package com.daqem.uilib.api.skilltree;

import com.daqem.uilib.api.widget.skilltree.ISkillTreeItemWidget;

import java.util.List;

public interface ISkillTreeItem {

    boolean isRoot();

    ISkillTreeItem getParent();
    void setParent(ISkillTreeItem parent);
    List<ISkillTreeItem> getChildren();
    void addChild(ISkillTreeItem child);
    void removeChild(ISkillTreeItem child);

    ISkillTreeItemWidget createWidget();

    float getX();
    float getY();
    void setLocation(float x, float y);
}
