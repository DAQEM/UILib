package com.daqem.uilib.skilltree;

import com.daqem.uilib.api.skilltree.ISkillTree;
import com.daqem.uilib.api.skilltree.ISkillTreeItem;

import java.util.List;

public abstract class AbstractSkillTree implements ISkillTree {

    private ISkillTreeItem root;
    private final List<ISkillTreeItem> items;
    private int skillTreeItemHeight = 32;
    private int skillTreeItemWidth = 32;
    private int horizontalSpacing = 8;
    private int verticalSpacing = 8;
    private int horizontalMargin = 8;
    private int verticalMargin = 8;

    public AbstractSkillTree(List<ISkillTreeItem> items) {
        this.items = items;
        this.root = this.getItems().stream()
                .filter(ISkillTreeItem::isRoot)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ISkillTreeItem getRoot() {
        return root;
    }

    @Override
    public void setRoot(ISkillTreeItem root) {
        this.root = root;
    }

    @Override
    public List<ISkillTreeItem> getItems() {
        return items;
    }

    @Override
    public void runPositioner() {
        if (this.root == null) return;
        SkillTreePositioner.run(this.root);
    }

    @Override
    public int getSkillTreeItemHeight() {
        return this.skillTreeItemHeight;
    }

    @Override
    public void setSkillTreeItemHeight(int skillTreeItemHeight) {
        this.skillTreeItemHeight = skillTreeItemHeight;
    }

    @Override
    public int getSkillTreeItemWidth() {
        return this.skillTreeItemWidth;
    }

    @Override
    public void setSkillTreeItemWidth(int skillTreeItemWidth) {
        this.skillTreeItemWidth = skillTreeItemWidth;
    }

    @Override
    public int getHorizontalSpacing() {
        return this.horizontalSpacing;
    }

    @Override
    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    @Override
    public int getVerticalSpacing() {
        return verticalSpacing;
    }

    @Override
    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    @Override
    public int getHorizontalMargin() {
        return horizontalMargin;
    }

    @Override
    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    @Override
    public int getVerticalMargin() {
        return verticalMargin;
    }

    @Override
    public void setVerticalMargin(int verticalMargin) {
        this.verticalMargin = verticalMargin;
    }
}
