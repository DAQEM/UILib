package com.daqem.uilib.skilltree;

import com.daqem.uilib.api.skilltree.ISkillTreeItem;

import java.util.List;

public abstract class AbstractSkillTreeItem implements ISkillTreeItem {

    private final boolean isRoot;
    private ISkillTreeItem parent;
    private final List<ISkillTreeItem> children;
    private float x;
    private float y;

    public AbstractSkillTreeItem(boolean isRoot, List<ISkillTreeItem> children) {
        this.isRoot = isRoot;
        this.children = children;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public boolean isRoot() {
        return this.isRoot;
    }

    @Override
    public ISkillTreeItem getParent() {
        return this.parent;
    }

    @Override
    public void setParent(ISkillTreeItem parent) {
        this.parent = parent;
    }

    @Override
    public List<ISkillTreeItem> getChildren() {
        return this.children;
    }

    @Override
    public void addChild(ISkillTreeItem child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public void removeChild(ISkillTreeItem child) {
        this.children.remove(child);
        child.setParent(null);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
