package com.daqem.uilib.api.skilltree;

import java.util.List;

public interface ISkillTree {

    ISkillTreeItem getRoot();
    void setRoot(ISkillTreeItem root);
    List<ISkillTreeItem> getItems();
    void runPositioner();

    int getSkillTreeItemHeight();
    void setSkillTreeItemHeight(int skillTreeItemHeight);

    int getSkillTreeItemWidth();
    void setSkillTreeItemWidth(int skillTreeItemWidth);

    int getHorizontalSpacing();
    void setHorizontalSpacing(int horizontalSpacing);

    int getVerticalSpacing();
    void setVerticalSpacing(int verticalSpacing);

    int getHorizontalMargin();
    void setHorizontalMargin(int horizontalMargin);

    int getVerticalMargin();
    void setVerticalMargin(int verticalMargin);
}
